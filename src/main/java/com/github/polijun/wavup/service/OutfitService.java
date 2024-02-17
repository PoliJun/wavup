package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Outfit;

import java.util.List;
import org.springframework.lang.NonNull;

public interface OutfitService {

    List<Outfit> getAllOutfits();

    Outfit getOutfitById(@NonNull Long outfitId);

    List<Outfit> getOutfitsByProduct(@NonNull Long productId);

    Outfit createOutfit(@NonNull Outfit outfit);

    void updateOutfit(@NonNull Long outfitId, @NonNull Outfit updatedOutfit);

    void deleteOutfit(@NonNull Long outfitId);
}
