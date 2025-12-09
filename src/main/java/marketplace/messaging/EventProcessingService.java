package marketplace.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(EventProcessingService.class);
    
    private boolean shouldThrowException = false;

    public void processCustomerEvent(CustomerEvent event) {
        switch (event.getEventType()) {
            case CREATED:
                handleCustomerCreated(event);
                break;
            case UPDATED:
                handleCustomerUpdated(event);
                break;
            case DELETED:
                handleCustomerDeleted(event);
                break;
        }
        
        if (shouldThrowException) {
            throw new RuntimeException("Simulated exception during customer event processing");
        }
    }

    public void processProductEvent(ProductEvent event) {
        switch (event.getEventType()) {
            case CREATED:
                handleProductCreated(event);
                break;
            case DELETED:
                handleProductDeleted(event);
                break;
        }
        
        if (shouldThrowException) {
            throw new RuntimeException("Simulated exception during product event processing");
        }
    }

    private void handleCustomerCreated(CustomerEvent event) {
        logger.info("Customer created - ID: {}, Name: {}, Money: {}", 
            event.getCustomerId(), event.getCustomerName(), event.getMoney());
        

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleCustomerUpdated(CustomerEvent event) {
        logger.info("Customer updated - ID: {}, Name: {}, Money: {}", 
            event.getCustomerId(), event.getCustomerName(), event.getMoney());
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleCustomerDeleted(CustomerEvent event) {
        logger.info("Customer deleted - ID: {}, Name: {}", 
            event.getCustomerId(), event.getCustomerName());
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleProductCreated(ProductEvent event) {
        logger.info("Product created - ID: {}, Name: {}, Price: {}, Owner: {} ({})", 
            event.getProductId(), event.getProductName(), event.getPrice(), 
            event.getOwnerName(), event.getOwnerId());
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleProductDeleted(ProductEvent event) {
        logger.info("Product deleted - ID: {}, Name: {}", 
            event.getProductId(), event.getProductName());
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void handleCustomerDLQ(CustomerEvent event) {
        logger.error("DLQ event: {}", event);
    }

    public void handleProductDLQ(ProductEvent event) {
        logger.error("DLQ event: {}", event);
    }

    public void setShouldThrowException(boolean shouldThrowException) {
        this.shouldThrowException = shouldThrowException;
    }
}


