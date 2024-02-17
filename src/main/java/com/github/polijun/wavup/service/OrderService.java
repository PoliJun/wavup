package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Order;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(@NonNull Long orderId);

    List<Order> getOrdersByUser(@NonNull Long userId);

    Order createOrder(@NonNull Order order);

    Order updateOrder(@NonNull Long orderId, @NonNull Order updatedOrder);

    void deleteOrder(@NonNull Long orderId);
}
