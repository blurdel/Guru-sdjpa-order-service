package com.blurdel.sdjpa.orderservice.services;


import com.blurdel.sdjpa.orderservice.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
}
