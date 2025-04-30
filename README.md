package com.example.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet filter to intercept all HTTP requests and decrypt the request body,
 * excluding specific endpoints like /login.
 */
@Slf4j // Lombok annotation for logging (replaces manual Logger definition)
@WebFilter("/*") // Filter applies to all incoming requests
public class DecryptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String uri = httpReq.getRequestURI();

        // Skip decryption for login requests
        if (uri.contains("/login")) {
            chain.doFilter(request, response);
        } else {
            try {
                // Wrap the request with decrypted content
                DecryptionRequestWrapper decryptedRequest = new DecryptionRequestWrapper(httpReq);
                chain.doFilter(decryptedRequest, response);
            } catch (Exception e) {
                log.error("Error decrypting request body: {}", e.getMessage(), e);
                throw new ServletException("Decryption failed");
            }
        }
    }
}


xxxxxx


package com.example.filter;

import com.example.util.AESGCMFrontendCompatible;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Custom request wrapper that reads the incoming request body,
 * decrypts encrypted fields recursively, and presents the modified input stream.
 */
@Slf4j
public class DecryptionRequestWrapper extends HttpServletRequestWrapper {

    private final String decryptedBody;

    public DecryptionRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        // Read the original request body as a String
        String originalBody = request.getReader().lines().reduce("", (acc, line) -> acc + line);

        // Convert the JSON string to Jackson's JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(originalBody);

        // Recursively decrypt fields inside the JSON
        JsonNode decryptedJson = decryptRecursive(root);

        // Convert decrypted JsonNode back to string
        this.decryptedBody = mapper.writeValueAsString(decryptedJson);
    }

    /**
     * Recursively traverses JSON nodes and decrypts string values.
     * @param node - Current JSON node
     * @return - Node with decrypted values
     */
    private JsonNode decryptRecursive(JsonNode node) {
        if (node.isObject()) {
            ObjectNode obj = (ObjectNode) node;
            obj.fieldNames().forEachRemaining(field ->
                obj.replace(field, decryptRecursive(obj.get(field)))
            );
            return obj;
        } else if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            for (int i = 0; i < array.size(); i++) {
                array.set(i, decryptRecursive(array.get(i)));
            }
            return array;
        } else if (node.isTextual()) {
            // Attempt decryption for textual values
            try {
                String decrypted = AESGCMFrontendCompatible.decrypt(node.asText(), AESGCMFrontendCompatible.BASE64_PASSWORD);
                return new TextNode(decrypted);
            } catch (Exception e) {
                log.debug("Skipping decryption for: {}", node.asText());
                return node;
            }
        } else {
            return node; // Leave other types (boolean, number) untouched
        }
    }

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

xxxxxx


<dependencies>
    <!-- Lombok for logging, boilerplate removal -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>

    <!-- Jackson for JSON parsing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.0</version>
    </dependency>
</dependencies>



{
  "username": "enVjdXUwM3Nxbmhrc3Fsdw==",
  "password": "c3VwZXJlbmNyeXB0ZWRwYXNz",
  "details": {
    "email": "am9obkBleGFtcGxlLmNvbQ==",
    "phone": "MTIzNDU2Nzg5MA=="
  }
}


