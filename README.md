package com.example.frn.controller;

import com.example.frn.service.FRNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

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
                // 📦 Bulk FRN check from Excel
                ByteArrayInputStream resultStream = frnService.processExcel(file);
                InputStreamResource resource = new InputStreamResource(resultStream);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=FRN_Result.xlsx")
                        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .body(resource);

            } else if (singleFrn != null && !singleFrn.trim().isEmpty()) {
                // 🔍 Single FRN check
                boolean exists = frnService.checkSingleFrn(singleFrn.trim());
                Map<String, Object> response = new HashMap<>();
                response.put("frn", singleFrn.trim());
                response.put("exists", exists);
                return ResponseEntity.ok(response);

            } else {
                return ResponseEntity.badRequest().body("Please upload a file or provide a FRN number.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error occurred: " + e.getMessage());
        }
    }
}