import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionCookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // You can leave this empty if no initialization is required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);
            if (session != null) {
                Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
                sessionCookie.setHttpOnly(true);
                sessionCookie.setSecure(true);
                sessionCookie.setPath(req.getContextPath());
                sessionCookie.setMaxAge(-1); // Make cookie non-persistent
                res.addCookie(sessionCookie);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // You can leave this empty if no cleanup is required
    }
}