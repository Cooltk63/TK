private String replacePlaceholders(String template, Map<String, String> data) {
    String result = template;

    // Match all {{KEY}} patterns
    Pattern pattern = Pattern.compile("\\{\\{\\s*(\\w+)\\s*\\}\\}");
    Matcher matcher = pattern.matcher(result);

    StringBuffer sb = new StringBuffer();

    while (matcher.find()) {
        String key = matcher.group(1); // extract key inside {{ }}
        String value = data.getOrDefault(key, ""); // fallback to empty string if not present
        matcher.appendReplacement(sb, Matcher.quoteReplacement(value));
    }

    matcher.appendTail(sb);
    return sb.toString();
}