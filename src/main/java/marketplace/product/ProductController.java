package marketplace.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import marketplace.messaging.EventProducer;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EventProducer eventProducer;
	
	@GetMapping
	public ResponseEntity<List<Product>> all() {
		List<Product> products = this.productService.getAll();
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Product> getBookById(@PathVariable Long id) {
        Optional<Product> product = this.productService.getById(id);
        
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("@securityManager.isUser() or @securityManager.isAdmin()")
    public Product createProduct(@RequestBody Product product) {
        Product savedProduct = this.productService.createOne(product);
        eventProducer.sendProductCreatedEvent(savedProduct);
        return savedProduct;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@securityManager.isAdmin()")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOpt = this.productService.getById(id);
        
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            Long ownerId = product.getOwner() != null ? product.getOwner().getId() : null;
            String ownerName = product.getOwner() != null ? product.getOwner().getName() : null;
            
            this.productService.deleteById(id);
            
            eventProducer.sendProductDeletedEvent(
                product.getId(),
                product.getName(),
                product.getPrice(),
                ownerId,
                ownerName
            );
        } else {
            this.productService.deleteById(id);
        }
        
        return ResponseEntity.noContent().build();
    }
}
