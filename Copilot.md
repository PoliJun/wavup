```java
package com.github.polijun.wavup.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Style
 */
@Entity(name = "Style")
@Table(name = "style",
        uniqueConstraints = {
                @UniqueConstraint(name = "style_name_unique", columnNames = "style_name")})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String styleName;

    @ManyToMany(mappedBy = "styles")
    private List<Product> products;
}
```

This is an Entity, please give me the service interface.  Declare methods.
