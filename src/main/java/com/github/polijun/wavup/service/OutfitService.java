package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Outfit;

import java.util.List;
import java.util.Optional;

public interface OutfitService {

    List<Outfit> getAllOutfits();

    Optional<Outfit> getOutfitById(Long outfitId);

    List<Outfit> getOutfitsByProduct(Long productId);

    Outfit createOutfit(Outfit outfit);

    Outfit updateOutfit(Long outfitId, Outfit updatedOutfit);

    void deleteOutfit(Long outfitId);
}
