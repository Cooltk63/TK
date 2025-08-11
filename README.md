// Java 1.8 compatible HeaderSecurityUtils class
public class HeaderSecurityUtils {
    
    /**
     * Sanitizes filename for HTTP headers to prevent header injection attacks
     * @param filename The original filename
     * @return Sanitized filename safe for HTTP headers
     */
    public static String sanitizeFilenameForHeader(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            return "report";
        }
        
        // Remove or replace dangerous characters that could cause header injection
        String sanitized = filename
            .replaceAll("[\r\n\t]", "") // Remove line breaks and tabs
            .replaceAll("[\"\\\\]", "_") // Replace quotes and backslashes
            .replaceAll("[<>:|?*]", "_") // Replace other problematic chars
            .replaceAll("\\s+", "_") // Replace whitespace with underscore
            .replaceAll("_{2,}", "_") // Replace multiple underscores with single
            .trim();
        
        // Ensure filename doesn't start or end with underscore/dot
        sanitized = sanitized.replaceAll("^[._]+|[._]+$", "");
        
        // Limit length to prevent buffer overflow
        if (sanitized.length() > 100) {
            sanitized = sanitized.substring(0, 100);
        }
        
        // Ensure we have a valid filename
        if (sanitized.isEmpty()) {
            sanitized = "report";
        }
        
        return sanitized;
    }
    
    /**
     * Sets Content-Disposition header safely
     * @param response HttpServletResponse object
     * @param filename Filename to set
     * @param inline Whether to display inline (true) or as attachment (false)
     */
    public static void setContentDispositionHeader(HttpServletResponse response, String filename, boolean inline) {
        String sanitizedFilename = sanitizeFilenameForHeader(filename);
        String disposition = inline ? "inline" : "attachment";
        
        // Use RFC 6266 compliant header format
        String headerValue = disposition + "; filename=\"" + sanitizedFilename + "\"";
        
        response.setHeader("Content-Disposition", headerValue);
    }
    
    /**
     * Sets all security headers to prevent various attacks
     * @param response HttpServletResponse object
     */
    public static void setSecurityHeaders(HttpServletResponse response) {
        // Prevent clickjacking
        response.setHeader("X-Frame-Options", "DENY");
        
        // Prevent MIME type sniffing
        response.setHeader("X-Content-Type-Options", "nosniff");
        
        // Enable XSS protection
        response.setHeader("X-XSS-Protection", "1; mode=block");
        
        // Prevent caching of sensitive content
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}