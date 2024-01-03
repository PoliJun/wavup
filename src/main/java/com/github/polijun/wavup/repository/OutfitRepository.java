package com.github.polijun.wavup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Outfit;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {
}