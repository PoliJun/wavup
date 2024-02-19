package com.github.polijun.wavup.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.polijun.wavup.exception.ImageNotFoundException;


/**
 * ImageController
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    private static final String IMAGE_PATH = "src/main/resources/static/img/";

    // get image file by file name
    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        try {
            return ResponseEntity.ok().body(Files.readAllBytes(Paths.get(IMAGE_PATH + fileName)));
        } catch (Exception e) {
            throw new ImageNotFoundException();
        }

    }

}
