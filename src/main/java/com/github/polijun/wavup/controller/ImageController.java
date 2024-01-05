package com.github.polijun.wavup.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.exception.ResourceNotFound;
import java.net.MalformedURLException;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        try {
            Resource file = new UrlResource(Paths.get("path/to/images", filename).toUri());
            if (file.exists() || file.isReadable()) {
                return ResponseEntity.ok().body(file);
            } else {
                throw new ResourceNotFound("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFound("Invalid URL for file: " + filename + " " + e.getMessage());
        }
    }
}
