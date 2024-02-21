package com.github.polijun.wavup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Style;
import com.github.polijun.wavup.service.StyleService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * StyleController
 */
@RestController
@RequestMapping("/styles")
@RequiredArgsConstructor
public class StyleController {
    private final StyleService styleService;

    @GetMapping("/all")
    public List<Style> getAllStyles() {
        List<Style> styles = styleService.getAllStyles();
        return styles;
    }


}
