package marketplace.soap.product;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id"
})
@XmlRootElement(name = "GetProductRequest", namespace = "http://marketplace.com/products")
public class GetProductRequest {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

}

