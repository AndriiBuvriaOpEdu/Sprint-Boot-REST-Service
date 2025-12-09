package marketplace.soap.product;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "price",
    "ownerId"
})
@XmlRootElement(name = "CreateProductRequest", namespace = "http://marketplace.com/products")
public class CreateProductRequest {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected java.math.BigDecimal price;
    @XmlElement(required = false, type = Long.class, nillable = true)
    protected Long ownerId;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal value) {
        this.price = value;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long value) {
        this.ownerId = value;
    }

}

