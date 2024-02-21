package com.github.polijun.wavup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.security.user.User;
import com.github.polijun.wavup.service.OrderService;
import lombok.RequiredArgsConstructor;

/**
 * OrderController
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // get all orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            return ResponseEntity.ok().body(orderService.getAllOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get order by id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable @NonNull Long id) {
        try {
            return ResponseEntity.ok().body(orderService.getOrderById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get orders by user
    @PostMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByUser(@RequestBody @NonNull User user) {
        try {
            return ResponseEntity.ok().body(orderService.getOrdersByUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // create order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody @NonNull Order order) {
        Order orderCreated;
        try {
            orderCreated = orderService.createOrder(order);
            return ResponseEntity.ok().body(orderCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // update order
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable @NonNull Long id,
            @RequestBody @NonNull Order updatedOrder) {
        try {
            orderService.updateOrder(id, updatedOrder);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete order by id
    @PutMapping("/delete/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable @NonNull Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
