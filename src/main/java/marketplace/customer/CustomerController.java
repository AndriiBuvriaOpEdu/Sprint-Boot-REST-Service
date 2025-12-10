package marketplace.customer;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marketplace.messaging.EventProducer;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerRepository repository;
	private final EventProducer eventProducer;
	
	CustomerController(CustomerRepository repository, EventProducer eventProducer) {
		this.repository = repository;
		this.eventProducer = eventProducer;
	}
	
	@GetMapping
	List<Customer> all() {
		return this.repository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("@securityManager.isUser() or @securityManager.isAdmin()")
	Customer newCustomer(@RequestBody Customer newCustomer) {
		Customer savedCustomer = repository.save(newCustomer);
		eventProducer.sendCustomerCreatedEvent(savedCustomer);
		
		return savedCustomer;
	}
  
    @GetMapping("/{id}")
    Customer one(@PathVariable Long id) {
    	return repository.findById(id)
    			.orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@securityManager.isUser() or @securityManager.isAdmin()")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
    
	    return repository.findById(id)
	      .map(Customer -> {
	        Customer.setName(newCustomer.getName());
	        Customer.setMoney(newCustomer.getMoney());
	        Customer savedCustomer = repository.save(Customer);
	        eventProducer.sendCustomerUpdatedEvent(savedCustomer);

	        return savedCustomer;
	      })
	      .orElseGet(() -> {
	        Customer savedCustomer = repository.save(newCustomer);
	        eventProducer.sendCustomerCreatedEvent(savedCustomer);
			
	        return savedCustomer;
	      });
    }

	@DeleteMapping("/{id}")
	@PreAuthorize("@securityManager.isAdmin()")
	void deleteCustomer(@PathVariable Long id) {
		repository.findById(id).ifPresent(customer -> {
			repository.deleteById(id);
			eventProducer.sendCustomerDeletedEvent(
				customer.getId(),
				customer.getName(),
				customer.getMoney()
			);
		});
	}
} 
