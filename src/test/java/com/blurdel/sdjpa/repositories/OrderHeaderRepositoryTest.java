package com.blurdel.sdjpa.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.blurdel.sdjpa.orderservice.domain.OrderLine;
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


    @Test
    void testSaveOrderWIthLines() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
        OrderHeader saved = orderHeaderRepo.save(orderHeader);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);

        orderHeader.setOrderLines(Set.of(orderLine));
        orderLine.setOrderHeader(orderHeader);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getOrderLines());
        assertEquals(saved.getOrderLines().size(), 1);
    }
	
    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
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
