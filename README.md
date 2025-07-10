import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelGenerator {

    public static void generateExcel(List<Map<String, Object>> dataList, OutputStream outputStream) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            if (dataList == null || dataList.isEmpty()) {
                workbook.write(outputStream);
                return;
            }

            Set<String> headers = dataList.get(0).keySet();
            Row headerRow = sheet.createRow(0);

            // 🔶 Create Header Style with Border and Bold Font
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            addCellBorders(headerStyle);

            // Create Header Cells
            int colIndex = 0;
            for (String header : headers) {
                Cell cell = headerRow.createCell(colIndex++);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);
            }

            // 🔶 Create Data Cell Style with Border
            CellStyle dataStyle = workbook.createCellStyle();
            addCellBorders(dataStyle);

            // Populate Data Rows
            int rowIndex = 1;
            for (Map<String, Object> rowData : dataList) {
                Row row = sheet.createRow(rowIndex++);
                colIndex = 0;
                for (String header : headers) {
                    Cell cell = row.createCell(colIndex++);
                    Object value = rowData.get(header);

                    if (value instanceof Number) {
                        cell.setCellValue(Double.parseDouble(value.toString()));
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }

                    cell.setCellStyle(dataStyle);
                }
            }

            // Auto-size columns
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        }
    }

    // 🔶 Helper: Add Thin Borders to CellStyle
    private static void addCellBorders(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}