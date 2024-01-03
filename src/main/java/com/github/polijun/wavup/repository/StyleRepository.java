package com.github.polijun.wavup.repository;

import com.github.polijun.wavup.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByName(String name);
}


