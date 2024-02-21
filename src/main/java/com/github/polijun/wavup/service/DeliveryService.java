package com.github.polijun.wavup.service;

import java.util.List;
import org.springframework.lang.NonNull;
import com.github.polijun.wavup.model.Delivery;
import com.github.polijun.wavup.model.Order;

public interface DeliveryService {

    List<Delivery> getAllDeliveries();

    Delivery getDeliveryById(@NonNull Long deliveryId);

    Delivery getDeliveryByOrder(@NonNull Order order);

    Delivery createDelivery(@NonNull Delivery delivery);

    void updateDelivery(@NonNull Long deliveryId, @NonNull Delivery updatedDelivery);

    void deleteDelivery(@NonNull Long deliveryId);
}
