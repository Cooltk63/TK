import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession(false);
            if (session != null) {
                Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
                sessionCookie.setHttpOnly(true);
                sessionCookie.setSecure(true);
                sessionCookie.setPath(req.getContextPath());
                sessionCookie.setMaxAge(-1); // makes it non-persistent
                res.addCookie(sessionCookie);
            }
        }

        chain.doFilter(request, response);
    }
}



xxxx
<filter>
    <filter-name>SessionCookieFilter</filter-name>
    <filter-class>com.your.package.SessionCookieFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>SessionCookieFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>