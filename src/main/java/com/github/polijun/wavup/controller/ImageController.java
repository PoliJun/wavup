package com.github.polijun.wavup.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.exception.ResourceNotFound;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class ImageController {


    private final ResourceLoader resourceLoader;

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Resource file = resourceLoader.getResource("classpath:static/images/" + filename);
            if (file.exists() || file.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg; charset=UTF-8").body(file);
            } else {
                throw new ResourceNotFound("Could not read file: " + filename);
            }
        } catch (Exception e) {
            throw new ResourceNotFound("Invalid URL for file: " + filename + " " + e.getMessage());
        }
    }
}
