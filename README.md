[28/01, 3:31 pm] Falguni Nakhwa - TCS: import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HSTSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        }

        chain.doFilter(request, response);
    }
}
[28/01, 3:38 pm] Falguni Nakhwa - TCS: Strict-Transport-Security: max-age=31536000; includeSubDomains
[28/01, 3:45 pm] Falguni Nakhwa - TCS: <?xml version="1.0" encoding="UTF-8"?>
<weblogic-web-app xmlns="http://xmlns.oracle.com/weblogic/weblogic-web-app">
    <container-descriptor>
        <prefer-application-packages>
            <package-name>javax.servlet.*</package-name>
        </prefer-application-packages>
    </container-descriptor>
    <header-parameters>
        <header-parameter>
            <name>Strict-Transport-Security</name>
            <value>max-age=31536000; includeSubDomains</value>
        </header-parameter>
    </header-parameters>
</weblogic-web-app>