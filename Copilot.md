```java
package com.github.polijun.wavup.security.user;

import java.util.List;
import com.github.polijun.wavup.model.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Entity(name = "User")
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique_constraint", columnNames = "email")})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}

```

This is an Entity, please give me the service interface.  Declare methods.

```java
package com.github.polijun.wavup.model;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product
 */
@Entity(name = "Product")
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private BigDecimal price;
    
    private String description;

    private Integer inventory;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<Outfit> outfits;

    @ManyToMany
    @JoinTable(name = "product_style", joinColumns = @JoinColumn(name = "style_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Style> styles;
}
```

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

I want to find Product by Style. give me code of ProductRepository and StyleRepository interfaces.
