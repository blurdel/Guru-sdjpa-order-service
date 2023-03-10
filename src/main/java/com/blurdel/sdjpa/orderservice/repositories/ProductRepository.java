package com.blurdel.sdjpa.orderservice.repositories;

import com.blurdel.sdjpa.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);

}
