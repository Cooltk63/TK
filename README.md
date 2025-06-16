@Service
public class FRNService {

    @Autowired
    private FRNRepository frnRepository;

    public boolean checkSingleFrn(String frn) {
        return frnRepository.existsById(frn);
    }

    public ByteArrayInputStream processExcel(MultipartFile file) throws IOException {
        List<String> allFrns = new ArrayList<>();
        List<String> errorFrns = new ArrayList<>();

        // Read Excel
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                try {
                    if (cell != null) {
                        String frn = cell.getStringCellValue().trim();
                        if (!frn.isEmpty()) {
                            allFrns.add(frn);
                        }
                    }
                } catch (Exception e) {
                    errorFrns.add("Row " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        }

        List<FRN> existingFrns = frnRepository.findByFrnNumberIn(allFrns);
        Set<String> existingSet = existingFrns.stream()
                .map(FRN::getFrnNumber)
                .collect(Collectors.toSet());

        List<String> existing = new ArrayList<>();
        List<String> notExisting = new ArrayList<>();

        for (String frn : allFrns) {
            if (existingSet.contains(frn)) {
                existing.add(frn);
            } else {
                notExisting.add(frn);
            }
        }

        return generateExcel(existing, notExisting, errorFrns);
    }

    private ByteArrayInputStream generateExcel(List<String> existing, List<String> notExisting, List<String> errors) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet notExistSheet = workbook.createSheet("Not Existing FRN");
        for (int i = 0; i < notExisting.size(); i++) {
            Row row = notExistSheet.createRow(i);
            row.createCell(0).setCellValue(notExisting.get(i));
        }

        Sheet existSheet = workbook.createSheet("Existed FRN");
        for (int i = 0; i < existing.size(); i++) {
            Row row = existSheet.createRow(i);
            row.createCell(0).setCellValue(existing.get(i));
        }

        Sheet errorSheet = workbook.createSheet("Error FRN");
        for (int i = 0; i < errors.size(); i++) {
            Row row = errorSheet.createRow(i);
            row.createCell(0).setCellValue(errors.get(i));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}

xxxx

@RestController
@RequestMapping("/api/frn")
public class FRNController {

    @Autowired
    private FRNService frnService;

    @PostMapping("/check")
    public ResponseEntity<?> checkFRN(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "frn", required = false) String singleFrn
    ) {
        try {
            if (file != null && !file.isEmpty()) {
                // Process bulk via Excel
                ByteArrayInputStream resultStream = frnService.processExcel(file);
                InputStreamResource resource = new InputStreamResource(resultStream);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=FRN_Result.xlsx")
                        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .body(resource);
            } else if (singleFrn != null && !singleFrn.trim().isEmpty()) {
                // Process single FRN
                boolean exists = frnService.checkSingleFrn(singleFrn.trim());
                Map<String, Object> response = new HashMap<>();
                response.put("frn", singleFrn.trim());
                response.put("exists", exists);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("Please upload a file or provide a FRN number.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }
}

xxxx

public interface FRNRepository extends JpaRepository<FRN, String> {
    List<FRN> findByFrnNumberIn(List<String> frnList);
}