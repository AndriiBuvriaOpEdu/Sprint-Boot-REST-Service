package marketplace.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import marketplace.config.RabbitMQConfig;
import marketplace.customer.Customer;
import marketplace.product.Product;

@Service
public class EventProducer {

    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);
    
    private final RabbitTemplate rabbitTemplate;

    public EventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCustomerCreatedEvent(Customer customer) {
        CustomerEvent event = new CustomerEvent(
            customer.getId(),
            customer.getName(),
            customer.getMoney(),
            CustomerEvent.EventType.CREATED
        );
        sendCustomerEvent(event, RabbitMQConfig.CUSTOMER_CREATED_ROUTING_KEY);
    }

    public void sendCustomerUpdatedEvent(Customer customer) {
        CustomerEvent event = new CustomerEvent(
            customer.getId(),
            customer.getName(),
            customer.getMoney(),
            CustomerEvent.EventType.UPDATED
        );
        sendCustomerEvent(event, RabbitMQConfig.CUSTOMER_UPDATED_ROUTING_KEY);
    }

    public void sendCustomerDeletedEvent(Long customerId, String customerName, java.math.BigDecimal money) {
        CustomerEvent event = new CustomerEvent(
            customerId,
            customerName,
            money,
            CustomerEvent.EventType.DELETED
        );
        sendCustomerEvent(event, RabbitMQConfig.CUSTOMER_DELETED_ROUTING_KEY);
    }

    private void sendCustomerEvent(CustomerEvent event, String routingKey) {
        try {
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.MARKETPLACE_EXCHANGE,
                routingKey,
                event
            );
            logger.info("Sent customer event: {}", event);
        } catch (Exception e) {
            logger.error("Failed to send customer event", e);
            throw new RuntimeException("Failed to send customer event", e);
        }
    }

    public void sendProductCreatedEvent(Product product) {
        Long ownerId = product.getOwner() != null ? product.getOwner().getId() : null;
        String ownerName = product.getOwner() != null ? product.getOwner().getName() : null;
        
        ProductEvent event = new ProductEvent(
            product.getId(),
            product.getName(),
            product.getPrice(),
            ownerId,
            ownerName,
            ProductEvent.EventType.CREATED
        );
        sendProductEvent(event, RabbitMQConfig.PRODUCT_CREATED_ROUTING_KEY);
    }

    public void sendProductDeletedEvent(Long productId, String productName, java.math.BigDecimal price, Long ownerId, String ownerName) {
        ProductEvent event = new ProductEvent(
            productId,
            productName,
            price,
            ownerId,
            ownerName,
            ProductEvent.EventType.DELETED
        );
        sendProductEvent(event, RabbitMQConfig.PRODUCT_DELETED_ROUTING_KEY);
    }

    private void sendProductEvent(ProductEvent event, String routingKey) {
        try {
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.MARKETPLACE_EXCHANGE,
                routingKey,
                event
            );
            logger.info("Sent product event: {}", event);
        } catch (Exception e) {
            logger.error("Failed to send product event", e);
            throw new RuntimeException("Failed to send product event", e);
        }
    }
}


