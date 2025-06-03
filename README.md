package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Slf4j
public class DownloadController {

    @GetMapping("/download/moc")
    public ResponseEntity<Resource> downloadMocFile() {
        try {
            // Path to moc.csv inside WebContent/resources/document
            Path path = Paths.get("WebContent/resources/document/moc.csv").toAbsolutePath().normalize();
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                log.error("File not found at: {}", path);
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"moc.csv\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            log.error("Error accessing file", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}