import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter to intercept requests (except /login), decrypt JSON body, and forward it.
 */
@WebFilter(urlPatterns = "/*")
public class DecryptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String uri = httpReq.getRequestURI();

        // Skip decryption for /login
        if (uri.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        // Wrap and pass the decrypted request
        DecryptionRequestWrapper wrappedRequest = new DecryptionRequestWrapper(httpReq);
        chain.doFilter(wrappedRequest, response);
    }
}
xxxxxx


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.ServletInputStream;
import javax.servlet.ReadListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * Wrapper for HTTP request to replace encrypted JSON body with decrypted content.
 */
public class DecryptionRequestWrapper extends HttpServletRequestWrapper {

    private final String decryptedBody;

    public DecryptionRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String originalBody = extractBody(request);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(originalBody); // Parse JSON

        // Recursively decrypt all text nodes
        JsonNode decryptedJson = decryptRecursive(root);
        decryptedBody = mapper.writeValueAsString(decryptedJson); // Convert back to JSON string
    }

    // Helper to read raw request body
    private String extractBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

    // Recursively decrypt all textual fields
    private JsonNode decryptRecursive(JsonNode node) {
        if (node.isObject()) {
            ObjectNode obj = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> fields = obj.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                obj.replace(entry.getKey(), decryptRecursive(entry.getValue()));
            }
            return obj;
        } else if (node.isArray()) {
            ArrayNode arr = (ArrayNode) node;
            for (int i = 0; i < arr.size(); i++) {
                arr.set(i, decryptRecursive(arr.get(i)));
            }
            return arr;
        } else if (node.isTextual()) {
            try {
                String decrypted = AESGCMFrontendCompatible.decrypt(node.asText(), "juVI+XqX90tQSqYPAmtVxg==");
                return new TextNode(decrypted); // return decrypted string as JSON node
            } catch (Exception e) {
                return node; // fallback to original if decryption fails
            }
        } else {
            return node; // leave non-text fields untouched
        }
    }

    // Return decrypted input stream
    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(decryptedBody.getBytes(StandardCharsets.UTF_8));

        return new ServletInputStream() {
            public int read() {
                return byteStream.read();
            }

            public boolean isFinished() {
                return byteStream.available() == 0;
            }

            public boolean isReady() {
                return true;
            }

            public void setReadListener(ReadListener readListener) {}
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}

xxx


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<DecryptionFilter> registerDecryptionFilter() {
        FilterRegistrationBean<DecryptionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DecryptionFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}


xxxx

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.14.2</version> <!-- or latest compatible -->
</dependency>