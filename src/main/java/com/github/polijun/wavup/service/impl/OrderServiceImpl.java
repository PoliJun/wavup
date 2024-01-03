package com.github.polijun.wavup.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.exception.ResourceNotFound;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.repository.OrderRepository;
import com.github.polijun.wavup.service.OrderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderById(Long id, Order order) {
        Order persistedOrder =
                orderRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Order"));
        persistedOrder.setBill(order.getBill());
        persistedOrder.setQuantity(order.getQuantity());
        persistedOrder.setStatus(order.getStatus());
        persistedOrder.setProducts(order.getProducts());
        persistedOrder.setUser(order.getUser());
        persistedOrder.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(persistedOrder);

    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }


    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Order"));
    }

}
