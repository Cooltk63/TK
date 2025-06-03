import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@Slf4j
public class DicgcReportController {

    @RequestMapping("/dicgcreport/downloadGranular")
    public void downloadGranularCsv(HttpServletResponse response) {
        log.info("Inside downloadGranularCsv method");

        try {
            // 🔽 This should point to your WebContent/resources/document/granular.csv
            File file = new File("WebContent/resources/document/granular.csv");

            if (!file.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
                return;
            }

            byte[] csvContent = FileUtils.readFileToByteArray(file);

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment;filename=Granular.csv");

            OutputStream out = response.getOutputStream();
            out.write(csvContent);
            out.close();
            response.flushBuffer();

            log.info("Granular CSV downloaded successfully");

        } catch (FileNotFoundException e) {
            log.error("File not found exception occurred", e);
        } catch (IOException e) {
            log.error("IOException occurred", e);
        } catch (Exception e) {
            log.error("General exception occurred", e);
        }
    }
}