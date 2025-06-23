private String wrapAsHtml(String bodyText, Map<String, String> data) {
    // Convert raw text into paragraphs
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
            ".header { width: 100%; display: flex; justify-content: space-between; margin-bottom: 20px; }" +
            ".header div { font-weight: bold; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='header'>" +
            "<div>Date: " + data.getOrDefault("DATE", "") + "</div>" +
            "<div>Ref. No.: " + data.getOrDefault("REF_NO", "") + "</div>" +
            "</div>" +
            contentBuilder.toString() +
            "</body></html>";
}