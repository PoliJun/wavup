package com.github.polijun.wavup.service;

import java.util.List;
import com.github.polijun.wavup.model.Outfit;

public interface OutfitService {
    List<Outfit> findAllOutfits();

    Outfit findOutfitById(Long id);

    Outfit createOutfit(Outfit outfit);

    Outfit updateOutfit(Long id, Outfit outfit);

    void deleteOutfitById(Long id);
}
