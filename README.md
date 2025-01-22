package com.tcs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JWTTokenAuthFilter extends OncePerRequestFilter {
    public static final String JWT_KEY = "JWT-TOKEN-SECRET"; // Secure JWT key
    private static final List<Pattern> AUTH_ROUTES = new ArrayList<>();
    private static final List<String> NO_AUTH_ROUTES = new ArrayList<>();
    private static final List<Pattern> NO_AUTH_ROUTES_PATTERNS = new ArrayList<>();

    static {
        AUTH_ROUTES.add(Pattern.compile("/BS/*")); // Routes requiring authentication
        NO_AUTH_ROUTES.add("/BS/Security/login");
        NO_AUTH_ROUTES.add("/BS/Security/logout");
        NO_AUTH_ROUTES.add("/BS/Security/reNewSession");
        NO_AUTH_ROUTES.add("/BS/index.jsp");
        NO_AUTH_ROUTES.add("/BS/views/login.jsp");
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/resources/*"));
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/assets/*"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        String authenticationHeader = request.getHeader("authentication");
        String route = request.getRequestURI();

        // Determine if authentication is required
        boolean needsAuthentication = isAuthRequired(route);

        if (needsAuthentication) {
            // Resolve the Authorization header
            String authHeader = resolveAuthHeader(authorizationHeader, authenticationHeader);

            // Validate the Authorization header
            if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
                handleUnauthorized(response, "Missing or invalid Authorization header.");
                return; // Stop further processing
            }

            final String token = authHeader.substring(7); // Remove "Bearer " prefix
            try {
                // Parse the token
                final Claims claims = Jwts.parser()
                        .setSigningKey(JWT_KEY)
                        .parseClaimsJws(token).getBody();

                // Validate the session (userId)
                boolean sessionValid = validateSession(request, claims);
                if (!sessionValid) {
                    handleUnauthorized(response, "Token mismatch. User session and token do not match.");
                    return; // Stop further processing
                }

                // Attach claims to the request
                request.setAttribute("claims", claims);

                // Proceed with the request
                filterChain.doFilter(request, response);

            } catch (Exception e) {
                handleUnauthorized(response, "Invalid token. Cause: " + e.getMessage());
            }
        } else {
            // Skip authentication for public routes
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Determines if authentication is required for the given route.
     */
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
        return route.startsWith("/BS/"); // Default to requiring authentication for /BS/*
    }

    /**
     * Resolves the Authorization header from the incoming request.
     */
    private String resolveAuthHeader(String authorizationHeader, String authenticationHeader) {
        if (StringUtils.isNotBlank(authorizationHeader)) {
            return authorizationHeader;
        } else if (StringUtils.isNotBlank(authenticationHeader)) {
            return authenticationHeader;
        }
        return null;
    }

    /**
     * Validates the session by checking if the userId in the token matches the session userId.
     */
    private boolean validateSession(HttpServletRequest request, Claims claims) {
        String userIdFromToken = claims.get("userId", String.class); // Key from your frontend JWT payload
        String userIdFromSession = (String) request.getSession().getAttribute("userId");

        if (userIdFromSession == null || !userIdFromSession.equals(userIdFromToken)) {
            System.err.println("User mismatch detected. Possible token manipulation.");
            return false;
        }
        return true;
    }

    /**
     * Handles unauthorized access by setting the response and redirecting to the logout page.
     */
    private void handleUnauthorized(HttpServletResponse response, String message) throws IOException {
        System.err.println("Unauthorized Access: " + message); // Log the error for debugging
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set the status code to 401
        response.sendRedirect("/BS/Security/logout"); // Redirect the user to the logout page
    }
}