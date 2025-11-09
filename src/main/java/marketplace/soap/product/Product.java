package marketplace.soap.product;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
    "id",
    "name",
    "price",
    "ownerId"
})
public class Product {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected java.math.BigDecimal price;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

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

