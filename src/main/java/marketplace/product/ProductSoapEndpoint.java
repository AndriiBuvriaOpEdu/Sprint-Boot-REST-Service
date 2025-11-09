package marketplace.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import marketplace.customer.CustomerRepository;
import marketplace.soap.product.*;

@Endpoint
public class ProductSoapEndpoint {

    private static final String NAMESPACE_URI = "http://marketplace.com/products";

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ProductSoapEndpoint(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        
        Product product = productRepository.findById(request.getId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        response.setProduct(convertToSoapProduct(product));
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        
        productRepository.findAll().forEach(product -> {
            response.getProducts().add(convertToSoapProduct(product));
        });
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateProductRequest")
    @ResponsePayload
    public CreateProductResponse createProduct(@RequestPayload CreateProductRequest request) {
        CreateProductResponse response = new CreateProductResponse();
        
        marketplace.customer.Customer owner = null;
        if (request.getOwnerId() != null) {
            owner = customerRepository.findById(request.getOwnerId())
                .orElse(null);
        }
        
        Product newProduct = owner != null 
            ? new Product(request.getName(), request.getPrice(), owner)
            : new Product(request.getName(), request.getPrice());
        
        Product savedProduct = productRepository.save(newProduct);
        response.setProduct(convertToSoapProduct(savedProduct));
        
        return response;
    }

    private marketplace.soap.product.Product convertToSoapProduct(Product product) {
        marketplace.soap.product.Product soapProduct = new marketplace.soap.product.Product();
        soapProduct.setId(product.getId());
        soapProduct.setName(product.getName());
        soapProduct.setPrice(java.math.BigDecimal.valueOf(product.getPrice().doubleValue()));
        soapProduct.setOwnerId(product.getOwner() != null ? product.getOwner().getId() : null);
        return soapProduct;
    }
}

