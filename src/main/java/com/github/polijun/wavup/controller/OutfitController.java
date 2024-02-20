package com.github.polijun.wavup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Outfit;
import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.service.OutfitService;
import lombok.RequiredArgsConstructor;

/**
 * OutfitController
 */
@RestController
@RequestMapping("/outfits")
@RequiredArgsConstructor
public class OutfitController {
    private final OutfitService outfitService;

    // get all outfits
    @GetMapping("/all")
    public ResponseEntity<List<Outfit>> getAllOutfits() {
        try {
            return ResponseEntity.ok().body(outfitService.getAllOutfits());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get outfit by id
    @GetMapping("/{id}")
    public ResponseEntity<Outfit> getOutfitById(@PathVariable @NonNull Long id) {
        try {
            return ResponseEntity.ok().body(outfitService.getOutfitById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get by product
    @PostMapping("/product")
    public ResponseEntity<List<Outfit>> getOutfitsByProduct(@RequestBody @NonNull Product product) {
        try {
            return ResponseEntity.ok().body(outfitService.getOutfitsByProduct(product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // create outfit
    @PostMapping("/create/Admin")
    public ResponseEntity<Outfit> createOutfit(@RequestBody @NonNull Outfit outfit) {
        Outfit outfitCreated;
        try {
            outfitCreated = outfitService.createOutfit(outfit);
            return ResponseEntity.ok().body(outfitCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // update outfit
    @PutMapping("/update/{id}/Admin")
    public ResponseEntity<Outfit> updateOutfit(@PathVariable @NonNull Long id,
            @RequestBody @NonNull Outfit updatedOutfit) {
        try {
            outfitService.updateOutfit(id, updatedOutfit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete outfit
    @DeleteMapping("/delete/{id}/Admin")
    public ResponseEntity<Outfit> deleteOutfit(@PathVariable @NonNull Long id) {
        try {
            outfitService.deleteOutfit(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
