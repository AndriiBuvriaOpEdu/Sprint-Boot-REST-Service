Лабораторна робота на тему Spring boot rest service 2

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
</soapenv:Envelope
