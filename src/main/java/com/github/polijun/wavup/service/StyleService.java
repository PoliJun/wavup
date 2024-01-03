package com.github.polijun.wavup.service;

import java.util.List;
import com.github.polijun.wavup.model.Style;

public interface StyleService {
    
    Style createStyle(Style style);

    Style findStyleById(Long id);

    List<Style> findAllStyle();

    Style finStyleByName(String name);

    Style updateStyle(Style style);

    void deleteStyle(Long id);
}
