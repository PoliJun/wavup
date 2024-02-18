package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.model.Style;

import java.util.List;
import java.util.Optional;

public interface StyleService {

    List<Style> getAllStyles();

    Style getStyleByStyleName(String styleName);

    Style getStyleById(Long styleId);

    // List<Style> getStylesByProduct(Product product);

    Style createStyle(Style style);

    void updateStyle(Long styleId, Style updatedStyle);

    void deleteStyle(Long styleId);
}
