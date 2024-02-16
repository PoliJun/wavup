package com.github.polijun.wavup.model;

import java.time.LocalDateTime;
import java.util.List;
import com.github.polijun.wavup.security.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

}
