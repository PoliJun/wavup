package com.github.polijun.wavup.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.exception.ResourceNotFound;
import com.github.polijun.wavup.model.Style;
import com.github.polijun.wavup.repository.StyleRepository;
import com.github.polijun.wavup.service.StyleService;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    @Override
    public Style createStyle(Style style) {
        return styleRepository.save(style);
    }

    @Override
    public Style findStyleById(Long id) {
        return styleRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Style"));
    }

    @Override
    public List<Style> findAllStyle() {
        return styleRepository.findAll();
    }

    @Override
    public Style finStyleByName(String name) {
        return styleRepository.findByName(name).orElseThrow(() -> new ResourceNotFound("Style"));
    }

    @Override
    public Style updateStyle(Style style) {
        Style foundStyle = styleRepository.findById(style.getId())
                .orElseThrow(() -> new ResourceNotFound("Style"));
                foundStyle.setName(style.getName());
                foundStyle.setProducts(style.getProducts());
        return styleRepository.save(foundStyle);
    }

    @Override
    public void deleteStyle(Long id) {
        styleRepository.deleteById(id);
    }
}
