private String wrapAsHtml(String bodyText, Map<String, String> data) {
    // Convert plain text lines to paragraphs
    String[] lines = bodyText.split("\n");
    StringBuilder contentBuilder = new StringBuilder();
    for (String line : lines) {
        if (line.trim().isEmpty()) {
            contentBuilder.append("<br/>");
        } else {
            contentBuilder.append("<p>").append(line.trim()).append("</p>");
        }
    }

    return "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Helvetica, sans-serif; font-size: 12pt; margin: 50px; line-height: 1.6; }" +
            "h1, h2, h3 { text-align: center; }" +
            "p { margin-bottom: 10px; }" +
            ".header { width: 100%; display: flex; justify-content: space-between; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='header'>" +
            "<div><strong>Date:</strong> " + data.getOrDefault("DATE", "") + "</div>" +
            "<div><strong>Ref. No.:</strong> " + data.getOrDefault("REF_NO", "") + "</div>" +
            "</div>" +
            "<br/><br/>" +
            contentBuilder +
            "</body></html>";
}