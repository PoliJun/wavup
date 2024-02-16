package com.github.polijun.wavup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Outfit;
import java.util.List;


@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {

    List<Outfit> findByProduc(Long productId);

}