@Service
public class PdfTemplateServiceImpl implements PdfTemplateService {

    /**
     * Generates PDF from plain text template and returns Base64.
     *
     * @param plainTemplate FE sent plain text with {{KEY}} placeholders
     * @param data          Key-value pairs to replace placeholders
     * @return Base64 string of the generated PDF
     * @throws Exception if PDF generation fails
     */
    @Override
    public String generatePdfBase64(String plainTemplate, Map<String, String> data) throws Exception {
        // Step 1: Replace placeholders with actual values
        String filledText = replacePlaceholders(plainTemplate, data);

        // Step 2: Convert plain text to basic HTML (replace line breaks with <br/>)
        String htmlContent = wrapAsHtml(filledText);

        // Step 3: Generate PDF from HTML
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();

        InputStream htmlStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlStream, StandardCharsets.UTF_8);

        document.close();

        // Step 4: Encode PDF as Base64
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    /**
     * Replaces {{KEY}} placeholders with actual values from map
     */
    private String replacePlaceholders(String template, Map<String, String> data) {
        String result = template;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return result;
    }

    /**
     * Wraps plain text as minimal HTML and replaces \n with <br/> for formatting
     */
    private String wrapAsHtml(String text) {
        String htmlBody = text.replaceAll("\n", "<br/>");
        return "<html><body style='font-family:Helvetica; font-size:12px;'>" + htmlBody + "</body></html>";
    }
}


xxx


@RestController
@RequestMapping("/api/pdf")
public class PdfTemplateController {

    @Autowired
    private PdfTemplateService pdfTemplateService;

    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generatePdf(@RequestBody PdfTemplateRequest request) {
        try {
            String base64 = pdfTemplateService.generatePdfBase64(request.getTemplate(), request.getData());
            return ResponseEntity.ok(Collections.singletonMap("base64", base64));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}