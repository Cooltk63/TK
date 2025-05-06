import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyJsonFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(httpRequest);

        String body = new BufferedReader(wrappedRequest.getReader())
                .lines()
                .collect(java.util.stream.Collectors.joining(System.lineSeparator()));

        System.out.println("JSON Payload: " + body);

        chain.doFilter(wrappedRequest, response);
    }
}