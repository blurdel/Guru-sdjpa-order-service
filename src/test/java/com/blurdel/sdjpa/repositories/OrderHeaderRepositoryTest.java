package com.blurdel.sdjpa.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.blurdel.sdjpa.orderservice.domain.Customer;
import com.blurdel.sdjpa.orderservice.domain.OrderApproval;
import com.blurdel.sdjpa.orderservice.domain.OrderLine;
import com.blurdel.sdjpa.orderservice.domain.Product;
import com.blurdel.sdjpa.orderservice.domain.ProductStatus;
import com.blurdel.sdjpa.orderservice.repositories.CustomerRepository;
import com.blurdel.sdjpa.orderservice.repositories.OrderApprovalRepository;
import com.blurdel.sdjpa.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.blurdel.sdjpa.orderservice.domain.OrderHeader;
import com.blurdel.sdjpa.orderservice.repositories.OrderHeaderRepository;

import java.util.Set;

@ActiveProfiles("mysql")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

	@Autowired
    OrderHeaderRepository orderHeaderRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderApprovalRepository orderApprovalRepo;

    @Autowired
    ProductRepository productRepo;

    Product product;

    @BeforeEach
    void setUp() {
        Product newProd = new Product();
        newProd.setProductStatus(ProductStatus.NEW);
        newProd.setDescription("test product");
        product = productRepo.saveAndFlush(newProd);
    }

    @Test
    void testSaveOrderWIthLines() {
        OrderHeader orderHeader = new OrderHeader();

        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        orderHeader.setCustomer(customer);
        Customer savedCustomer = customerRepo.save(customer);

        orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        // Here we use the helper method to set the relationship @OneToMany and @ManyToOne
        orderHeader.addOrderLine(orderLine);

        OrderHeader saved = orderHeaderRepo.save(orderHeader);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getOrderLines());
        assertEquals(saved.getOrderLines().size(), 1);


        OrderHeader fetched = orderHeaderRepo.getById(saved.getId());

        assertNotNull(fetched);
        assertEquals(fetched.getOrderLines().size(), 1);
    }
	
    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();

        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        orderHeader.setCustomer(customer);
        Customer savedCustomer = customerRepo.save(customer);

        orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        OrderApproval approval = new OrderApproval();
        approval.setApprovedBy("me");
        OrderApproval savedApproval = orderApprovalRepo.save(approval);
        orderHeader.setOrderApproval(savedApproval);

        OrderHeader saved = orderHeaderRepo.save(orderHeader);

        assertNotNull(saved);
        assertNotNull(saved.getId());

        OrderHeader fetched = orderHeaderRepo.getById(saved.getId());

        assertNotNull(fetched);
        assertNotNull(fetched.getId());
        assertNotNull(fetched.getCreatedDate());
        assertNotNull(fetched.getLastModifiedDate());

        assertAll(
                () -> assertNotNull(fetched),
                () -> assertNotNull(fetched.getId())
        );
    }
    
}
