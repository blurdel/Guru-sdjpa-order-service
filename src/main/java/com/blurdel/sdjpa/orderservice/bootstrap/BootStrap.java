package com.blurdel.sdjpa.orderservice.bootstrap;


import com.blurdel.sdjpa.orderservice.domain.Customer;
import com.blurdel.sdjpa.orderservice.domain.OrderHeader;
import com.blurdel.sdjpa.orderservice.repositories.CustomerRepository;
import com.blurdel.sdjpa.orderservice.repositories.OrderHeaderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    // Created this class to:
    // Allow us to work with Spring data repositories in a normal Spring context
    // Work-around since Spring Boot @Test get a Transaction wrapped around them

//    @Autowired
//    OrderHeaderRepository orderHeaderRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

//    @Transactional
//    public void readOrderData() {
//        OrderHeader orderHeader = orderHeaderRepo.findById(101L).get(); // implicit transaction
//
//        orderHeader.getOrderLines().forEach((ol -> {
//            System.out.println(ol.getProduct().getDescription());
//
//            ol.getProduct().getCategories().forEach(cat -> {
//                System.out.println(cat.getDescription()); // This lazy load will fail w/o @Transactional
//            });
//        }));
//    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("BootStrap was called");
//        readOrderData();
        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer saved = customerRepo.save(customer);
        System.out.println("Version is " + saved.getVersion());

        saved.setCustomerName("Testing Version 2");
        Customer saved2 =  customerRepo.save(saved);
        System.out.println("Version is " + saved2.getVersion());

        saved2.setCustomerName("Testing Version 3");
        Customer saved3 = customerRepo.save(saved2);
        System.out.println("Version is " + saved3.getVersion());

        customerRepo.deleteById(saved.getId());
    }

}
