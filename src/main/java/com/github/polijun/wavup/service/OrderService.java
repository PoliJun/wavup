package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.security.user.User;
import java.util.List;
import org.springframework.lang.NonNull;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(@NonNull Long orderId);

    List<Order> getOrdersByUser(@NonNull User user);

    Order createOrder(@NonNull Order order);

    void updateOrder(@NonNull Long orderId, @NonNull Order updatedOrder);

    void deleteOrder(@NonNull Long orderId);
}
