package com.github.polijun.wavup.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.polijun.wavup.exception.AlreadyExistsException;
import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.model.Style;
import com.github.polijun.wavup.repository.StyleRepository;
import com.github.polijun.wavup.service.StyleService;
import lombok.RequiredArgsConstructor;

/**
 * StyleServiceImpl
 */
@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;
    private static final String STYLE = "Style";

    @Override
    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    @Override
    public Style getStyleByStyleName(String styleName) {
        return styleRepository.findByStyleName(styleName)
                .orElseThrow(() -> new NonExistsException(STYLE));
    }

    @Override
    public Style getStyleById(Long styleId) {
        return styleRepository.findById(styleId).orElseThrow(() -> new NonExistsException(STYLE));
    }

    @Override
    public Style createStyle(Style style) {
        String styleName = Objects.requireNonNull(style.getStyleName());
        Optional<Style> styleExists = styleRepository.findByStyleName(styleName);
        if (!styleExists.isPresent()) {
            return styleRepository.save(style);
        } else {
            throw new AlreadyExistsException(STYLE);
        }
    }

    @Override
    @Transactional
    public void updateStyle(Long styleId, Style updatedStyle) {
        Style styleExists =
                styleRepository.findById(styleId).orElseThrow(() -> new NonExistsException(STYLE));
        styleExists.setProducts(updatedStyle.getProducts());
        styleExists.setStyleName(updatedStyle.getStyleName());
    }

    @Override
    public void deleteStyle(Long styleId) {
        Optional<Style> styleExists = styleRepository.findById(styleId);
        if (styleExists.isPresent()) {
            styleRepository.deleteById(styleId);
        } else {
            throw new NonExistsException(STYLE);
        }
    }

}
