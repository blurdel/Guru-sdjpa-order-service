package com.blurdel.sdjpa.orderservice.repositories;

import com.blurdel.sdjpa.orderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blurdel.sdjpa.orderservice.domain.OrderHeader;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findAllByCustomer(Customer customer);

}
