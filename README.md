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
    public static final String JWT_KEY = "JWT-TOKEN-SECRET";
    private static final List<Pattern> AUTH_ROUTES = new ArrayList<>();
    private static final List<String> NO_AUTH_ROUTES = new ArrayList<>();
    private static final List<Pattern> NO_AUTH_ROUTES_PATTERNS = new ArrayList<>();

    static {
        AUTH_ROUTES.add(Pattern.compile("/BS/*"));
        NO_AUTH_ROUTES.add("/BS/Security/login");
        NO_AUTH_ROUTES.add("/BS/Security/logout");
        NO_AUTH_ROUTES.add("/BS/Security/reNewSession");
        NO_AUTH_ROUTES.add("/BS/index.jsp");
        NO_AUTH_ROUTES.add("/BS/views/login.jsp");
        NO_AUTH_ROUTES.add("/BS/pdfStream.jsp");
        NO_AUTH_ROUTES.add("/BS/displaySignedPDF.jsp");
        NO_AUTH_ROUTES.add("/BS/signPDF.jsp");
        NO_AUTH_ROUTES.add("/BS/favicon.ico");
        NO_AUTH_ROUTES.add("/BS/signapplet.jar.pack.gz");
        NO_AUTH_ROUTES.add("/BS/Admin/downloadSignedReport");
        NO_AUTH_ROUTES.add("/BS/displayPDF.jsp");
        NO_AUTH_ROUTES.add("/BS/acceptReport.jsp");
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/resources/*"));
        NO_AUTH_ROUTES_PATTERNS.add(Pattern.compile("/BS/assets/*"));
    }

    /*private Logger LOG = LoggerFactory.getLogger(JWTTokenAuthFilter.class);*/

    /*@Autowired
    private UserService userService;*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        String authenticationHeader = request.getHeader("authentication");
        String route = request.getRequestURI();

        // no auth route matching
        boolean needsAuthentication = false;
        for (Pattern p : AUTH_ROUTES) {
            if (p.matcher(route).matches()) {
                needsAuthentication = true;
                break;
            }
        }

        if (route.startsWith("/BS/")) {
            needsAuthentication = true;
        }

        if (NO_AUTH_ROUTES.contains(route)) {
            needsAuthentication = false;
        }

        for (Pattern p : NO_AUTH_ROUTES_PATTERNS) {
            if (p.matcher(route).find()) {
                needsAuthentication = false;
                break;
            }
        }
        // Checking whether the current route needs to be authenticated
        if (needsAuthentication) {
            // Check for authorization header presence
            String authHeader = null;
            if (authorizationHeader == null || authorizationHeader.equalsIgnoreCase("")) {
                if (authenticationHeader == null || authenticationHeader.equalsIgnoreCase("")) {
                    authHeader = null;
                } else {
                    authHeader = authenticationHeader;
                }
            } else {
                authHeader = authorizationHeader;
            }

            if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
                throw new AuthCredentialsMissingException("Missing or invalid Authorization header.");
            }

            final String token = authHeader.substring(7); // The part after "Bearer "

            logger.info("token === " + token);
            try {
                final Claims claims = Jwts.parser().setSigningKey(JWT_KEY)
                        .parseClaimsJws(token).getBody();


                // ✅ Validate the user session (using `userId` key from frontend)
                boolean validation = validateSession(request, claims, response);
                if (!validation) {
                    logger.info("validation fail");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    logger.info("validateSession redirected");
                    //response.sendRedirect("/BS/Security/logout");
                    response.sendRedirect("/BS/index.jsp");
                    logger.info("validateSession redirected successfully");
                    return;

                }
                logger.info("Returned setAttribute");
                    request.setAttribute("claims", claims);
                    // Now since the authentication process if finished
                    // move the request forward

                logger.info("Returned setAttribute successfully");
                    filterChain.doFilter(request, response);


            } catch (final Exception e) {
                throw new AuthenticationFailedException("Invalid token. Cause:" + e.getMessage());
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    // Validate the session (userId key from JWT matches session userId)
    private boolean validateSession(HttpServletRequest request, Claims claims, HttpServletResponse response) throws Exception {
        String userIdFromToken = claims.get("userId", String.class); // Key from your frontend JWT payload
        String userIdFromSession = (String) request.getSession().getAttribute("userId");

        logger.info("userIdFromToken" + userIdFromToken);
        logger.info("userIdFromSession" + userIdFromSession);

        if (userIdFromSession == null || !userIdFromSession.equals(userIdFromToken)) {
            System.err.println("User mismatch detected. Possible token manipulation.");
            return false;
//

        }
        return true;
    }
}

