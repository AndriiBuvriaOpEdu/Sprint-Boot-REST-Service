Для лаби 3

Докер

docker run -d --hostname my-rabbit --name some-rabbit -p 8080:15672 -p 5672:5672 rabbitmq:3-management

REST ендпоынти для девтесту
POST http://localhost:8081/api/customers

DELETE http://localhost:8081/api/customers/1



SOAP:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
   <soapenv:Body xmlns="http://marketplace.com/customers">
      <GetAllCustomersRequest/>
   </soapenv:Body>
</soapenv:Envelope>

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
   <soapenv:Body xmlns="http://marketplace.com/customers">
      <CreateCustomerRequest>
         <name>John Doe</name>
         <money>1000.50</money>
      </CreateCustomerRequest>
   </soapenv:Body>
</soapenv:Envelope>

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
   <soapenv:Body xmlns="http://marketplace.com/products">
      <GetAllProductsRequest/>
   </soapenv:Body>
</soapenv:Envelope>

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
   <soapenv:Body xmlns="http://marketplace.com/products">
      <GetProductRequest>
         <id>1</id>
      </GetProductRequest>
   </soapenv:Body>
</soapenv:Envelope>

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
   <soapenv:Body xmlns="http://marketplace.com/products">
      <CreateProductRequest>
         <name>Laptop</name>
         <price>999.99</price>
         <ownerId>1</ownerId>
      </CreateProductRequest>
   </soapenv:Body>
</soapenv:Envelope>

