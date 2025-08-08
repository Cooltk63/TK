private String sanitizeString(String input) {
    return input != null ? input.replaceAll("[^a-zA-Z0-9_\\-]", "") : "";
}

private String sanitizeDate(String input) {
    return input != null && input.matches("\\d{2}/\\d{2}/\\d{4}") ? input : "01/01/1970";
}

private String sanitizeFileName(String input) {
    return input != null ? input.replaceAll("[^a-zA-Z0-9_.\\-]", "") : "default";
}



String circleCode = sanitizeString((String) list.get("circleCode"));
String userId = sanitizeString((String) list.get("userId"));
String quarterEndDate = sanitizeDate((String) list.get("quarterEndDate"));
String jrxmlName = sanitizeFileName((String) reportList.get("jrxmlName"));
String reportMasterId = sanitizeString((String) reportList.get("reportMasterId"));


HttpSession session = request.getSession();

byte[] safePdfContent = pdfContent; // pdf generated internally, so considered trusted
String safeMessage = "Operationcompletedsuccessfully";

// Only store trusted values in session
if (safePdfContent != null && safePdfContent.length > 0) {
    session.setAttribute("pdfContent", safePdfContent);
}
session.setAttribute("message", safeMessage);

return safePdfContent;


