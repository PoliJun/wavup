package com.github.polijun.wavup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Delivery;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.service.DeliveryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    // get all deliveries
    @GetMapping("/all")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        try {
            return ResponseEntity.ok().body(deliveryService.getAllDeliveries());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get delivery by id
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable @NonNull Long id) {
        try {
            return ResponseEntity.ok().body(deliveryService.getDeliveryById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get delivery by order
    @PostMapping("/order")
    public ResponseEntity<Delivery> getDeliveryByOrder(@RequestBody @NonNull Order order) {
        try {
            return ResponseEntity.ok().body(deliveryService.getDeliveryByOrder(order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // create delivery
    @PostMapping("/create/auth")
    public ResponseEntity<Delivery> createDelivery(@RequestBody @NonNull Delivery delivery) {
        try {
            return ResponseEntity.ok().body(deliveryService.createDelivery(delivery));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // update delivery
    @PutMapping("/update/{id}/auth")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable @NonNull Long id,
            @RequestBody @NonNull Delivery updatedDelivery) {
        try {
            deliveryService.updateDelivery(id, updatedDelivery);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete delivery
    @DeleteMapping("/delete/{id}/auth")
    public ResponseEntity<Delivery> deleteDelivery(@PathVariable @NonNull Long id) {
        try {
            deliveryService.deleteDelivery(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
