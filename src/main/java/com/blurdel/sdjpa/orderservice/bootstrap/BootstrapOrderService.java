package com.blurdel.sdjpa.orderservice.bootstrap;

import com.blurdel.sdjpa.orderservice.domain.OrderHeader;
import com.blurdel.sdjpa.orderservice.repositories.OrderHeaderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootstrapOrderService {

    @Autowired
    OrderHeaderRepository orderHeaderRepo;

    // Demonstrates a proxy mode, an external method being called

    @Transactional
    public void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepo.findById(101L).get(); // implicit transaction

        orderHeader.getOrderLines().forEach((ol -> {
            System.out.println(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription()); // This lazy load will fail w/o @Transactional
            });
        }));
    }

}
