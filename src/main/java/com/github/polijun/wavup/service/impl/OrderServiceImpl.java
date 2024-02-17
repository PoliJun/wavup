package com.github.polijun.wavup.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.repository.OrderRepository;
import com.github.polijun.wavup.service.OrderService;
import lombok.RequiredArgsConstructor;

/**
 * OrderServiceImpl
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public Order getOrderById(@NonNull Long orderId) {
      return orderRepository.findById(orderId).orElseThrow();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUser(userId);}

    @Override
    public Order createOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

    @Override
    public void deleteOrder(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    
}