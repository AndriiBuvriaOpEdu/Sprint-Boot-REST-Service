package marketplace.customer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerRepository repository;
	
	CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	List<Customer> all() {
		return this.repository.findAll();
	}
	
	@PostMapping
	Customer newCustomer(@RequestBody Customer newCustomer) {
		return repository.save(newCustomer);
	}
  
    @GetMapping("/{id}")
    Customer one(@PathVariable Long id) {
    	return repository.findById(id)
    			.orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
    
	    return repository.findById(id)
	      .map(Customer -> {
	        Customer.setName(newCustomer.getName());
	        Customer.setMoney(newCustomer.getMoney());
	        return repository.save(Customer);
	      })
	      .orElseGet(() -> {
	        return repository.save(newCustomer);
	      });
    }

	@DeleteMapping("/{id}")
	void deleteCustomer(@PathVariable Long id) {
		repository.deleteById(id);
	}
} 
