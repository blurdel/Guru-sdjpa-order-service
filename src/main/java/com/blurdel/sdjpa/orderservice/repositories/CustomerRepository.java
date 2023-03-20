package com.blurdel.sdjpa.orderservice.repositories;

import com.blurdel.sdjpa.orderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);

}
