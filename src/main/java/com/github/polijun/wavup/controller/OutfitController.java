package com.github.polijun.wavup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Outfit;
import com.github.polijun.wavup.service.OutfitService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/outfit")
@AllArgsConstructor
public class OutfitController {
    private final OutfitService outfitService;

    @PostMapping("/create")
    public ResponseEntity<Outfit> createOutfit(@RequestBody Outfit outfit) {
        Outfit savedOutfit = outfitService.createOutfit(outfit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOutfit);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Outfit>> getAllOutfit() {
        List<Outfit> foundedOutfit = outfitService.findAllOutfits();
        return ResponseEntity.ok(foundedOutfit);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Outfit> getOutfitById(@PathVariable Long id) {
        Outfit foundOutfit = outfitService.findOutfitById(id);
        return ResponseEntity.ok(foundOutfit);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Outfit> updateOutfitById(@PathVariable Long id,
            @RequestBody Outfit outfit) {
        Outfit updatedOutfit = outfitService.updateOutfit(id, outfit);
        return ResponseEntity.ok(updatedOutfit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOutfitById(@PathVariable Long id) {
        outfitService.deleteOutfitById(id);
        return ResponseEntity.ok("item has been deleted.");
    }
}
