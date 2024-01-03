package com.github.polijun.wavup.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.exception.ResourceNotFound;
import com.github.polijun.wavup.model.Outfit;
import com.github.polijun.wavup.repository.OutfitRepository;
import com.github.polijun.wavup.service.OutfitService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OutfitServiceImpl implements OutfitService {

    private final OutfitRepository outfitRepository;

    @Override
    public List<Outfit> findAllOutfits() {
        return outfitRepository.findAll();
    }

    @Override
    public Outfit findOutfitById(Long id) {
        return outfitRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Outfit"));
    }

    @Override
    public Outfit createOutfit(Outfit outfit) {
        return outfitRepository.save(outfit);
    }

    @Override
    public Outfit updateOutfit(Long id, Outfit outfit) {

        Outfit persistedOutfit =
                outfitRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Outfit"));
        return outfitRepository.save(persistedOutfit);
    }

    @Override
    public void deleteOutfitById(Long id) {
        outfitRepository.deleteById(id);
    }


}
