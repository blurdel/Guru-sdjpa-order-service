package com.blurdel.sdjpa.orderservice.services;

import com.blurdel.sdjpa.orderservice.domain.Product;
import com.blurdel.sdjpa.orderservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public Product saveProduct(Product product) {
        return productRepo.saveAndFlush(product);
    }

    @Transactional
    @Override
    public Product updateQOH(Long id, Integer quantityOnHand) {
        Product fetched = productRepo.findById(id)
                .orElseThrow();

        fetched.setQuantityOnHand(quantityOnHand);

        return productRepo.saveAndFlush(fetched);
    }
}
