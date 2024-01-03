package com.github.polijun.wavup.service;

import java.util.List;
import com.github.polijun.wavup.model.Order;

public interface OrderService {

    List<Order> findAllOrders();

    Order createOrder(Order order);

    Order updateOrder(Long id, Order order);

    void deleteOrderById(Long id);
}
