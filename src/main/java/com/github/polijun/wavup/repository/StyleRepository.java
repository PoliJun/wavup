package com.github.polijun.wavup.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Style;

/**
 * StyleRepository
 */
@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByName(String styleName);

    Optional<Style> findByStyleName(Long styleName);

    Optional<Style> findByStyleName(String styleName);


}
