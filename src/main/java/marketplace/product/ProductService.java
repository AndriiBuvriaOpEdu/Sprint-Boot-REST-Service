package marketplace.product;

import java.util.Optional;

import org.springframework.stereotype.Service;

import marketplace.core.BaseService;
import marketplace.customer.Customer;

@Service
public class ProductService extends BaseService<Product, ProductRepository> {
	
	ProductService(ProductRepository repository) {
		super(repository);
	}
	
	
	public void sellProduct(Long id, Customer buyer) {		
		Optional<Product> productOpt = this.getById(id);

		if (productOpt.isEmpty()) {
		    throw new RuntimeException("Product not found by " + id);
		}

		Product product = productOpt.get();
		Customer productsOwner = product.getOwner();
	
		
		if (productsOwner == null) {
			product.setOwner(buyer);
			
			this.repostiory.save(product);
		}
		
		
		throw new RuntimeException("Product is already owned by another customer " + buyer.getName());
	}

}
