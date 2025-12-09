package marketplace.soap.product;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "product"
})
@XmlRootElement(name = "GetProductResponse", namespace = "http://marketplace.com/products")
public class GetProductResponse {

    @XmlElement(required = true)
    protected Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product value) {
        this.product = value;
    }

}

