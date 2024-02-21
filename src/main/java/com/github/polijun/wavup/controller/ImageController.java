package com.github.polijun.wavup.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.polijun.wavup.exception.ImageNotFoundException;
import com.github.polijun.wavup.service.OutfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * ImageController
 */
@Controller
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final OutfitService outfitService;

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getMethodName(@PathVariable String fileName) {
        try {
            return ResponseEntity.ok().body(outfitService.getOutfitImage(fileName));
        } catch (ImageNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
