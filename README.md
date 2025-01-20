package com.tcs.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.ExpiredJwtException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JWTTokenAuthFilter extends OncePerRequestFilter {
    private static final List<Pattern> AUTH_ROUTES = new ArrayList<>();
    private static final List<String> NO_AUTH_ROUTES = new ArrayList<>();
    private static final List<Pattern> NO_AUTH_ROUTES_PATTERNS = new ArrayList<>();
    public static final String JWT_KEY = "JWT-TOKEN-SECRET"; // Secure this key in production

    static {
        AUTH_ROUTES.add(Pattern.compile("/BS/*")); // Protected routes
        NO_AUTH_ROUTES.add("/BS/Security/login");  // Public routes
        NO_AUTH_ROUTES.add("/BS/Security/logout");
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/resources/*"));
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/assets/*"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String authenticationHeader = request.getHeader("Authentication");
        String route = request.getRequestURI();

        // Determine if the route requires authentication
        boolean needsAuthentication = isAuthRequired(route);

        if (needsAuthentication) {
            // Resolve the token from headers
            String authHeader = resolveAuthHeader(authorizationHeader, authenticationHeader);

            if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
                return;
            }

            final String token = authHeader.substring(7); // Remove "Bearer " prefix

            try {
                // ✅ Validate the JWT token
                Claims claims = Jwts.parser()
                        .setSigningKey(JWT_KEY.getBytes()) // Ensure the key is securely managed
                        .parseClaimsJws(token)
                        .getBody();

                // ✅ Validate the user session (using `userId` key from frontend)
                validateSession(request, claims);

                // Attach claims to the request for further use in controllers
                request.setAttribute("claims", claims);

                // Proceed with the filter chain
                filterChain.doFilter(request, response);

            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has expired.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
            }
        } else {
            // If no authentication is required, continue the filter chain
            filterChain.doFilter(request, response);
        }
    }

    // Check if the route requires authentication
    private boolean isAuthRequired(String route) {
        for (Pattern p : AUTH_ROUTES) {
            if (p.matcher(route).matches()) {
                return true;
            }
        }
        if (NO_AUTH_ROUTES.contains(route)) {
            return false;
        }
        for (Pattern p : NO_AUTH_ROUTES_PATTERNS) {
            if (p.matcher(route).find()) {
                return false;
            }
        }
        return route.startsWith("/BS/"); // Default to authentication required for /BS/*
    }

    // Resolve the authorization header
    private String resolveAuthHeader(String authorizationHeader, String authenticationHeader) {
        if (StringUtils.isNotBlank(authorizationHeader)) {
            return authorizationHeader;
        } else if (StringUtils.isNotBlank(authenticationHeader)) {
            return authenticationHeader;
        }
        return null;
    }

    // Validate the session (userId key from JWT matches session userId)
    private void validateSession(HttpServletRequest request, Claims claims) throws Exception {
        String userIdFromToken = claims.get("userId", String.class); // Key from your frontend JWT payload
        String userIdFromSession = (String) request.getSession().getAttribute("userId");

        if (userIdFromSession == null || !userIdFromSession.equals(userIdFromToken)) {
            throw new Exception("User mismatch detected. Possible token manipulation.");
        }
    }
}