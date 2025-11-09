package marketplace.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import marketplace.soap.customer.*;

@Endpoint
public class CustomerSoapEndpoint {

    private static final String NAMESPACE_URI = "http://marketplace.com/customers";

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerSoapEndpoint(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
        GetCustomerResponse response = new GetCustomerResponse();
        
        marketplace.customer.Customer customer = customerRepository.findById(request.getId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        response.setCustomer(convertToSoapCustomer(customer));
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCustomersRequest")
    @ResponsePayload
    public GetAllCustomersResponse getAllCustomers(@RequestPayload GetAllCustomersRequest request) {
        GetAllCustomersResponse response = new GetAllCustomersResponse();
        
        customerRepository.findAll().forEach(customer -> {
            response.getCustomers().add(convertToSoapCustomer(customer));
        });
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateCustomerRequest")
    @ResponsePayload
    public CreateCustomerResponse createCustomer(@RequestPayload CreateCustomerRequest request) {
        CreateCustomerResponse response = new CreateCustomerResponse();
        
        marketplace.customer.Customer newCustomer = new marketplace.customer.Customer(
            request.getName(), 
            request.getMoney()
        );
        
        Customer savedCustomer = customerRepository.save(newCustomer);
        response.setCustomer(convertToSoapCustomer(savedCustomer));
        
        return response;
    }

    private marketplace.soap.customer.Customer convertToSoapCustomer(marketplace.customer.Customer customer) {
        marketplace.soap.customer.Customer soapCustomer = new marketplace.soap.customer.Customer();
        soapCustomer.setId(customer.getId());
        soapCustomer.setName(customer.getName());
        soapCustomer.setMoney(java.math.BigDecimal.valueOf(customer.getMoney().doubleValue()));
        return soapCustomer;
    }
}

