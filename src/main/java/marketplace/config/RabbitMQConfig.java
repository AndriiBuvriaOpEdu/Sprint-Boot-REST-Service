package marketplace.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String MARKETPLACE_EXCHANGE = "marketplace.exchange";
    
    public static final String CUSTOMER_EVENTS_QUEUE = "customer.events.queue";
    public static final String PRODUCT_EVENTS_QUEUE = "product.events.queue";
    public static final String CUSTOMER_DLQ = "customer.events.dlq";
    public static final String PRODUCT_DLQ = "product.events.dlq";
    
    public static final String CUSTOMER_CREATED_ROUTING_KEY = "customer.created";
    public static final String CUSTOMER_UPDATED_ROUTING_KEY = "customer.updated";
    public static final String CUSTOMER_DELETED_ROUTING_KEY = "customer.deleted";
    public static final String PRODUCT_CREATED_ROUTING_KEY = "product.created";
    public static final String PRODUCT_DELETED_ROUTING_KEY = "product.deleted";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return factory;
    }

    @Bean
    public TopicExchange marketplaceExchange() {
        return new TopicExchange(MARKETPLACE_EXCHANGE, true, false);
    }

    @Bean
    public Queue customerEventsQueue() {
        return QueueBuilder.durable(CUSTOMER_EVENTS_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", CUSTOMER_DLQ)
                .withArgument("x-message-ttl", 60000)
                .build();
    }

    @Bean
    public Queue productEventsQueue() {
        return QueueBuilder.durable(PRODUCT_EVENTS_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", PRODUCT_DLQ)
                .withArgument("x-message-ttl", 60000) 
                .build();
    }

    @Bean
    public Queue customerDLQ() {
        return QueueBuilder.durable(CUSTOMER_DLQ).build();
    }

    @Bean
    public Queue productDLQ() {
        return QueueBuilder.durable(PRODUCT_DLQ).build();
    }

    @Bean
    public Binding customerCreatedBinding() {
        return BindingBuilder
                .bind(customerEventsQueue())
                .to(marketplaceExchange())
                .with(CUSTOMER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding customerUpdatedBinding() {
        return BindingBuilder
                .bind(customerEventsQueue())
                .to(marketplaceExchange())
                .with(CUSTOMER_UPDATED_ROUTING_KEY);
    }

    @Bean
    public Binding customerDeletedBinding() {
        return BindingBuilder
                .bind(customerEventsQueue())
                .to(marketplaceExchange())
                .with(CUSTOMER_DELETED_ROUTING_KEY);
    }

    @Bean
    public Binding productCreatedBinding() {
        return BindingBuilder
                .bind(productEventsQueue())
                .to(marketplaceExchange())
                .with(PRODUCT_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding productDeletedBinding() {
        return BindingBuilder
                .bind(productEventsQueue())
                .to(marketplaceExchange())
                .with(PRODUCT_DELETED_ROUTING_KEY);
    }
}


