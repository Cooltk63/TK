package com.tcs.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class JWTTokenAuthFilter extends OncePerRequestFilter {

    private static List<Pattern> AUTH_ROUTES = new ArrayList<>();
    private static List<String> NO_AUTH_ROUTES = new ArrayList<>();
    private static List<Pattern> NO_AUTH_ROUTES_PATTERNS = new ArrayList<>();
    public static final String JWT_KEY = "JWT-TOKEN-SECRET";  // Keep this secure

    static {
        AUTH_ROUTES.add(Pattern.compile("/BS/*"));
        NO_AUTH_ROUTES.add("/BS/Security/login");
        NO_AUTH_ROUTES.add("/BS/Security/logout");
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/resources/*"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String route = request.getRequestURI();
        boolean needsAuthentication = AUTH_ROUTES.stream().anyMatch(p -> p.matcher(route).matches());

        if (needsAuthentication) {
            if (StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
                throw new AuthCredentialsMissingException("Missing or invalid Authorization header.");
            }

            final String token = authorizationHeader.substring(7);

            try {
                // ✅ Validate Token
                final Claims claims = Jwts.parser()
                        .setSigningKey(JWT_KEY.getBytes())
                        .parseClaimsJws(token)
                        .getBody();

                // ✅ Session Check (Prevents Token Swapping)
                String userIdFromToken = claims.get("userId", String.class);
                String userIdFromSession = (String) request.getSession().getAttribute("userId");

                if (userIdFromSession == null || !userIdFromSession.equals(userIdFromToken)) {
                    throw new AuthenticationFailedException("User mismatch detected. Possible token manipulation.");
                }

                // ✅ Role Check (Approver Access)
                String roleFromToken = claims.get("role", String.class);
                if (route.contains("/approveRequest") && !"APPROVER".equals(roleFromToken)) {
                    throw new AuthenticationFailedException("Unauthorized action: Insufficient role.");
                }

                // ✅ Device Binding (Prevents Token Theft)
                String deviceIdFromToken = claims.get("deviceId", String.class);
                String currentDeviceId = request.getHeader("User-Agent");
                if (!deviceIdFromToken.equals(currentDeviceId)) {
                    throw new AuthenticationFailedException("Device mismatch detected.");
                }

                // ✅ Proceed if all checks pass
                filterChain.doFilter(request, response);

            } catch (Exception e) {
                throw new AuthenticationFailedException("Invalid token: " + e.getMessage());
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}




####updateApp

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push(function ($q, $window) {
        return {
            request: function (config) {
                var token = $window.localStorage.getItem('token');
                var deviceId = navigator.userAgent;  // ✅ AppSec Change: Device Binding

                if (token) {
                    try {
                        var payload = JSON.parse(atob(token.split('.')[1]));
                        var currentTime = Math.floor(Date.now() / 1000);

                        // ✅ AppSec Change: Token Expiration Check
                        if (payload.exp && payload.exp < currentTime) {
                            console.warn('Token expired');
                            $window.localStorage.removeItem('token');
                        } else {
                            // ✅ AppSec Change: Attach Token and Device ID
                            config.headers.Authorization = 'Bearer ' + token;
                            config.headers['X-Device-Id'] = deviceId;
                        }
                    } catch (e) {
                        console.error('Invalid token:', e.message);
                        $window.localStorage.removeItem('token');
                    }
                }
                return config;
            }
        };
    });
});



###updatedMainCtrl 

app.controller('MainCtrl', function ($scope, $window) {
    var token = $window.localStorage.getItem('token');

    if (token) {
        var payload = JSON.parse(atob(token.split('.')[1]));

        // ✅ AppSec Change: UI Access Control
        $scope.isApprover = payload.role === 'APPROVER';

        if (!$scope.isApprover) {
            // Disable or hide sensitive UI actions for non-approvers
            document.getElementById('approveBtn').disabled = true;
            document.getElementById('rejectBtn').disabled = true;
        }
    }
});

