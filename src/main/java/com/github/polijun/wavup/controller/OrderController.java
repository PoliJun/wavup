package com.github.polijun.wavup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> foundedOrders = orderService.findAllOrders();
        return ResponseEntity.ok(foundedOrders);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order foundedOrder = orderService.findOrderById(id);
        return ResponseEntity.ok(foundedOrder);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrderById(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Product has been deleted.");
    }
}
