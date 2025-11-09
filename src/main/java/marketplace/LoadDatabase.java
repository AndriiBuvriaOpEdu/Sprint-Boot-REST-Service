package marketplace;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import marketplace.customer.Customer;
import marketplace.customer.CustomerRepository;
import marketplace.product.Product;
import marketplace.product.ProductRepository;

@Configuration
class LoadDatabase {
    public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, ProductRepository productRepository) {
        Customer rickCustomer = new Customer("Riki Cooper", BigDecimal.valueOf(300));
        
    	return args -> {
            log.info("Preloading " + customerRepository.save(new Customer("Jon Jones", BigDecimal.valueOf(100))));
            log.info("Preloading " + customerRepository.save(rickCustomer));

            log.info("Preloading " + productRepository.save(new Product("Toy", BigDecimal.valueOf(150), rickCustomer)));
            log.info("Preloading " + productRepository.save(new Product("Pen", BigDecimal.valueOf(50))));
            log.info("Preloading " + productRepository.save(new Product("TV", BigDecimal.valueOf(500))));
        };
    }
}
