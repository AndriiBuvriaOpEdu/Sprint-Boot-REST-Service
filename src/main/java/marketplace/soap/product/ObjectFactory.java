package marketplace.soap.product;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public GetProductRequest createGetProductRequest() {
        return new GetProductRequest();
    }

    public GetProductResponse createGetProductResponse() {
        return new GetProductResponse();
    }

    public GetAllProductsRequest createGetAllProductsRequest() {
        return new GetAllProductsRequest();
    }

    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

    public CreateProductRequest createCreateProductRequest() {
        return new CreateProductRequest();
    }

    public CreateProductResponse createCreateProductResponse() {
        return new CreateProductResponse();
    }

    public Product createProduct() {
        return new Product();
    }

}

