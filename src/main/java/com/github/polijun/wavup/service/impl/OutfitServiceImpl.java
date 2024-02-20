package com.github.polijun.wavup.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.model.Outfit;
import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.repository.OutfitRepository;
import com.github.polijun.wavup.service.OutfitService;
import lombok.RequiredArgsConstructor;

/**
 * OutfitServiceImpl
 */
@Service
@RequiredArgsConstructor
public class OutfitServiceImpl implements OutfitService {
    private final OutfitRepository outfitRepository;
    private static final String OUTFIT = "Outfit";

    @Override
    public List<Outfit> getAllOutfits() {
        return outfitRepository.findAll();
    }

    @Override
    public Outfit getOutfitById(@NonNull Long outfitId) {
        return outfitRepository.findById(outfitId)
                .orElseThrow(() -> new NonExistsException(OUTFIT));
    }

    @Override
    public List<Outfit> getOutfitsByProduct(@NonNull Product product) {
        return outfitRepository.findByProduct(product);
    }

    @Override
    public Outfit createOutfit(@NonNull Outfit outfit) {
        return outfitRepository.save(outfit);
    }

    @Override
    @Transactional
    public void updateOutfit(@NonNull Long outfitId, @NonNull Outfit updatedOutfit) {
        Outfit outfitExists = outfitRepository.findById(outfitId)
                .orElseThrow(() -> new NonExistsException(OUTFIT));
        outfitExists.setFileName(updatedOutfit.getFileName());
        outfitExists.setProduct(updatedOutfit.getProduct());
    }

    @Override
    public void deleteOutfit(@NonNull Long outfitId) {
        Optional<Outfit> outfitExists = outfitRepository.findById(outfitId);
        if (outfitExists.isPresent()) {
            outfitRepository.deleteById(outfitId);
        } else {
            throw new NonExistsException(OUTFIT);
        }
    }


}
