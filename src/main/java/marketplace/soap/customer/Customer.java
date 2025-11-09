package marketplace.soap.customer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "id",
    "name",
    "money"
})
public class Customer {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected java.math.BigDecimal money;

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

    public java.math.BigDecimal getMoney() {
        return money;
    }

    public void setMoney(java.math.BigDecimal value) {
        this.money = value;
    }

}

