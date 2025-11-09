package marketplace.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import marketplace.customer.Customer;

@Entity
public class Product {
	
	@Id
	@GeneratedValue 
	private Long id;
	
	
	private String name;
	private Number price;
	
	@ManyToOne(optional=true)
	private Customer owner;
	
	Product() {}
	
	public Product(String name, Number price, Customer owner) {
		this.name = name;
        this.price = price;
        this.owner = owner;
	}
	
	public Product(String name, Number price) {
		this(name, price, null);
	}
	
	public String getName() { return this.name; }
	public Long getId() { return this.id; }
	public Number getPrice() { return this.price; }
	public Customer getOwner() { return this.owner; }
	
	public void setName(String name) { this.name = name; }
	public void setPrice(Number price) { this.price = price; }
	public void setOwner(Customer owner) { this.owner = owner; }
	
	
}
