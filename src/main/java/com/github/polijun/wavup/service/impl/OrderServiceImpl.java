package com.github.polijun.wavup.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.repository.OrderRepository;
import com.github.polijun.wavup.service.OrderService;
import lombok.RequiredArgsConstructor;

/**
 * OrderServiceImpl
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(@NonNull Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    @Override
    public List<Order> getOrdersByUser(@NonNull Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order createOrder(@NonNull Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrder(@NonNull Long orderId, @NonNull Order updatedOrder) {
        Order orderExist = orderRepository.findById(orderId)
                .orElseThrow(() -> new NonExistsException("Order"));
        orderExist.setProducts(updatedOrder.getProducts());
        orderExist.setCreatedAt(updatedOrder.getCreatedAt());
        orderExist.setStatus(updatedOrder.getStatus());
        orderExist.setUser(updatedOrder.getUser());
        orderExist.setUpdatedAt(LocalDateTime.now());
        orderExist.setBill(updatedOrder.getBill());
    }

    @Override
    public void deleteOrder(@NonNull Long orderId) {
        Optional<Order> orderExist = orderRepository.findById(orderId);
        if (orderExist.isPresent()) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NonExistsException("Order");
        }
    }


}
