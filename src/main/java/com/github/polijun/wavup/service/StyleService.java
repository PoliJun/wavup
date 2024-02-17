package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Style;

import java.util.List;
import java.util.Optional;

public interface StyleService {

    List<Style> getAllStyles();

    Optional<Style> getStyleByStyleName(String styleName);

    Optional<Style> getStyleById(Long styleId);

    Style createStyle(Style style);

    Style updateStyle(Long styleId, Style updatedStyle);

    void deleteStyle(Long styleId);
}
