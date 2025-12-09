package marketplace.messaging;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerEvent {
    
    private Long customerId;
    private String customerName;
    private BigDecimal money;
    private EventType eventType;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public CustomerEvent() {
        this.timestamp = LocalDateTime.now();
    }

    public CustomerEvent(Long customerId, String customerName, BigDecimal money, EventType eventType) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.money = money;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public enum EventType {
        CREATED, UPDATED, DELETED
    }

    @Override
    public String toString() {
        return "CustomerEvent{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", money=" + money +
                ", eventType=" + eventType +
                ", timestamp=" + timestamp +
                '}';
    }
}


