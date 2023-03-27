package com.blurdel.sdjpa.repositories;

import com.blurdel.sdjpa.orderservice.domain.Product;
import com.blurdel.sdjpa.orderservice.domain.ProductStatus;
import com.blurdel.sdjpa.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("mysql")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepo;

    @Test
    void testGetCategory() {
        Product product = productRepo.findByDescription("Product 1").get();

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product saved = productRepo.save(product);

        Product fetched = productRepo.getById(saved.getId());

        assertNotNull(fetched);
        assertNotNull(fetched.getDescription());
        assertNotNull(fetched.getCreatedDate());
        assertNotNull(fetched.getLastModifiedDate());
    }

    @Test
    void addAndUpdateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product saved = productRepo.saveAndFlush(product);

        saved.setQuantityOnHand(25);

        Product saved2 = productRepo.saveAndFlush(saved);

        System.out.println("Quantity on hand: " + saved2.getQuantityOnHand());
    }

}