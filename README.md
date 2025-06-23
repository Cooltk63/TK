private String wrapAsHtml(String text, Map<String, String> data) {
    String htmlFormatted = text.replaceAll("\n", "<br/>");

    return "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Helvetica; font-size: 12px; }" +
            ".header { display: flex; justify-content: space-between; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='header'>" +
            "<div>Date: " + data.getOrDefault("DATE", "") + "</div>" +
            "<div>Ref. No.: " + data.getOrDefault("REF_NO", "") + "</div>" +
            "</div>" +
            "<br/><br/>" +
            htmlFormatted +
            "</body></html>";
}