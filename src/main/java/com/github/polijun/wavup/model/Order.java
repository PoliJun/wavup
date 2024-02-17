package com.github.polijun.wavup.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.github.polijun.wavup.security.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order
 */
@Entity(name = "Order")
@Table(name = "order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // @Transient
    private BigDecimal bill;

    public void setBill() {
        BigDecimal amount = new BigDecimal(0);
        if (this.products != null) {
            for (Product product : this.products) {
                amount = amount.add(product.getPrice());
            }

        }
        this.bill = amount;
    }

    public void setBill(BigDecimal amount) {
        this.bill = amount;
    }
}
