package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Style;

import java.util.List;
import org.springframework.lang.NonNull;

public interface StyleService {

    List<Style> getAllStyles();

    Style getStyleByStyleName(String styleName);

    Style getStyleById(@NonNull Long styleId);

    // List<Style> getStylesByProduct(Product product);

    Style createStyle(@NonNull Style style);

    void updateStyle(@NonNull Long styleId, @NonNull Style updatedStyle);

    void deleteStyle(@NonNull Long styleId);
}
