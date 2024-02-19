package com.github.polijun.wavup.service.impl;

import java.util.List;
import java.util.Objects;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.model.Delivery;
import com.github.polijun.wavup.model.Order;
import com.github.polijun.wavup.repository.DeliveryRepository;
import com.github.polijun.wavup.service.DeliveryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private static final String DELIVERY = "Delivery";

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getDeliveryById(@NonNull Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new NonExistsException(DELIVERY));
    }

    @Override
    public Delivery getDeliveryByOrder(@NonNull Order order) {
        return deliveryRepository.findByOrder(order)
                .orElseThrow(() -> new NonExistsException(DELIVERY));
    }

    @Override
    public Delivery createDelivery(@NonNull Delivery delivery) {
        Long deliveryId = Objects.requireNonNull(delivery.getId());
        if (deliveryRepository.existsById(deliveryId)) {
            throw new NonExistsException(DELIVERY);
        } else {
            return deliveryRepository.save(delivery);
        }
    }

    @Override
    @Transactional
    public void updateDelivery(@NonNull Long deliveryId, @NonNull Delivery updatedDelivery) {
        Delivery deliveryExists = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new NonExistsException(DELIVERY));
        deliveryExists.setCompany(updatedDelivery.getCompany());
        deliveryExists.setTrackingNumber(updatedDelivery.getTrackingNumber());
        deliveryExists.setOrder(updatedDelivery.getOrder());
    }

    @Override
    public void deleteDelivery(@NonNull Long deliveryId) {
        if (deliveryRepository.existsById(deliveryId)) {
            deliveryRepository.deleteById(deliveryId);
        } else {
            throw new NonExistsException(DELIVERY);
        }
    }


}
