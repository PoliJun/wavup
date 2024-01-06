package com.github.polijun.wavup.service;

import java.util.List;
import com.github.polijun.wavup.model.Order;

public interface OrderService {

    List<Order> findAllOrders();

    List<Order> findOrdersByUserId(Long userId);

    Order findOrderById(Long id);

    Order createOrder(Order order);

    Order updateOrderById(Long id, Order order);

    void deleteOrderById(Long id);
}
