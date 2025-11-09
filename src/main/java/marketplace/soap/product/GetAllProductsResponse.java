package marketplace.soap.product;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "products"
})
@XmlRootElement(name = "GetAllProductsResponse", namespace = "http://marketplace.com/products")
public class GetAllProductsResponse {

    @XmlElement(name = "product")
    protected List<Product> products;

    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<Product>();
        }
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

