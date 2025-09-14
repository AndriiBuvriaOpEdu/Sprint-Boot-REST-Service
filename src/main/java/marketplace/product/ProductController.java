package marketplace.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
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
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createOne(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
}
