package marketplace.soap.customer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "money"
})
@XmlRootElement(name = "CreateCustomerRequest", namespace = "http://marketplace.com/customers")
public class CreateCustomerRequest {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected java.math.BigDecimal money;

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

