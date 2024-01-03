package com.github.polijun.wavup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Style;
import com.github.polijun.wavup.service.StyleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/style")
@AllArgsConstructor
public class StyleController {

    private final StyleService styleService;

    @PostMapping("/create")
    public ResponseEntity<Style> createStyle(@RequestBody Style style) {
        Style createdStyle = styleService.createStyle(style);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStyle);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable Long id) {
        Style foundStyle = styleService.findStyleById(id);
        return ResponseEntity.ok(foundStyle);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Style>> getAllStyle() {
        List<Style> foundedStyles = styleService.findAllStyle();
        return ResponseEntity.ok(foundedStyles);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Style> updateStyle(@PathVariable Long id, @RequestBody Style style) {
        Style updatedStyle = styleService.updateStyle(style);
        return ResponseEntity.ok(updatedStyle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStyle(@PathVariable Long id) {
        styleService.deleteStyle(id);
        return ResponseEntity.ok("Style has been deleted.");
    }
}
