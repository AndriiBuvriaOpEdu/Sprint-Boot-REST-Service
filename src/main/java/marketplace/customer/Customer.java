package marketplace.customer;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	private @Id
	@GeneratedValue Long id;
	
	private String name;
	
	@Column(columnDefinition = "DECIMAL(19, 2)")
	private BigDecimal money;
	
	Customer() {}
	
	public Customer(String name, BigDecimal money) {
		this.name = name;
		this.money = money;
	}
	
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public BigDecimal getMoney() {
		return this.money;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.money);
	}
	
	
}
