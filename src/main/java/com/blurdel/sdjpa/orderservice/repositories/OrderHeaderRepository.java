package com.blurdel.sdjpa.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blurdel.sdjpa.orderservice.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
