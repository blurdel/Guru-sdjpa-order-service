package com.blurdel.sdjpa.orderservice.repositories;

import com.blurdel.sdjpa.orderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
