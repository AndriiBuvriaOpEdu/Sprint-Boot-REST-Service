package marketplace.soap.customer;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public GetCustomerRequest createGetCustomerRequest() {
        return new GetCustomerRequest();
    }

    public GetCustomerResponse createGetCustomerResponse() {
        return new GetCustomerResponse();
    }

    public GetAllCustomersRequest createGetAllCustomersRequest() {
        return new GetAllCustomersRequest();
    }

    public GetAllCustomersResponse createGetAllCustomersResponse() {
        return new GetAllCustomersResponse();
    }

    public CreateCustomerRequest createCreateCustomerRequest() {
        return new CreateCustomerRequest();
    }

    public CreateCustomerResponse createCreateCustomerResponse() {
        return new CreateCustomerResponse();
    }

    public Customer createCustomer() {
        return new Customer();
    }

}

