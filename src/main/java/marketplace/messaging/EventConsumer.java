package marketplace.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import marketplace.config.RabbitMQConfig;

@Component
public class EventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private static final int MAX_RETRIES = 3;
    
    private final EventProcessingService eventProcessingService;

    public EventConsumer(EventProcessingService eventProcessingService) {
        this.eventProcessingService = eventProcessingService;
    }

    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_EVENTS_QUEUE)
    public void handleCustomerEvent(
            @Payload CustomerEvent event,
            Message message,
            @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
            Channel channel) {
        
        logger.info("Received customer event: {}", event);
        
        try {
            eventProcessingService.processCustomerEvent(event);
            
            channel.basicAck(deliveryTag, false);
            
        } catch (Exception e) {
            logger.error("Failed to process customer event", e);
            
            Integer retryCount = getRetryCount(message);
            
            if (retryCount != null && retryCount < MAX_RETRIES) {
                logger.warn("Retry {}/{}", retryCount + 1, MAX_RETRIES);
                try {
                    channel.basicNack(deliveryTag, false, true);
                } catch (Exception nackEx) {
                    logger.error("Failed to nack message", nackEx);
                }
            } else {
                logger.error("Max retries reached, sending to DLQ");
                try {
                    channel.basicNack(deliveryTag, false, false);
                } catch (Exception nackEx) {
                    logger.error("Failed to send to DLQ", nackEx);
                }
            }
        }
    }

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_EVENTS_QUEUE)
    public void handleProductEvent(
            @Payload ProductEvent event,
            Message message,
            @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
            Channel channel) {
        
        logger.info("Received product event: {}", event);
        
        try {
            eventProcessingService.processProductEvent(event);
            
            channel.basicAck(deliveryTag, false);
            
        } catch (Exception e) {
            logger.error("Failed to process product event", e);
            
            Integer retryCount = getRetryCount(message);
            
            if (retryCount != null && retryCount < MAX_RETRIES) {
                logger.warn("Retry {}/{}", retryCount + 1, MAX_RETRIES);
                try {
                    channel.basicNack(deliveryTag, false, true);
                } catch (Exception nackEx) {
                    logger.error("Failed to nack message", nackEx);
                }
            } else {
                logger.error("Max retries reached, sending to DLQ");
                try {
                    channel.basicNack(deliveryTag, false, false);
                } catch (Exception nackEx) {
                    logger.error("Failed to send to DLQ", nackEx);
                }
            }
        }
    }

    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_DLQ)
    public void handleCustomerDLQ(@Payload CustomerEvent event) {
        logger.error("DLQ message: {}", event);
        eventProcessingService.handleCustomerDLQ(event);
    }

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_DLQ)
    public void handleProductDLQ(@Payload ProductEvent event) {
        logger.error("DLQ message: {}", event);
        eventProcessingService.handleProductDLQ(event);
    }

    private Integer getRetryCount(Message message) {
        Object retryCount = message.getMessageProperties().getHeaders().get("x-retry-count");
        if (retryCount instanceof Integer) {
            return (Integer) retryCount;
        }
        return null;
    }
}

