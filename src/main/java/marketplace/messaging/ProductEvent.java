package marketplace.messaging;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductEvent {
    
    private Long productId;
    private String productName;
    private BigDecimal price;
    private Long ownerId;
    private String ownerName;
    private EventType eventType;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ProductEvent() {
        this.timestamp = LocalDateTime.now();
    }

    public ProductEvent(Long productId, String productName, BigDecimal price, Long ownerId, String ownerName, EventType eventType) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
        CREATED, DELETED
    }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", eventType=" + eventType +
                ", timestamp=" + timestamp +
                '}';
    }
}


