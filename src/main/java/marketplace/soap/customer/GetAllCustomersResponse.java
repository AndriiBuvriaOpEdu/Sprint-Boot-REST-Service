package marketplace.soap.customer;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customers"
})
@XmlRootElement(name = "GetAllCustomersResponse", namespace = "http://marketplace.com/customers")
public class GetAllCustomersResponse {

    @XmlElement(name = "customer")
    protected List<Customer> customers;

    public List<Customer> getCustomers() {
        if (customers == null) {
            customers = new ArrayList<Customer>();
        }
        return this.customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}

