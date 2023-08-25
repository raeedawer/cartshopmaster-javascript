package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "provider")
@NoArgsConstructor
@AllArgsConstructor
public class Provider {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private ShippingPolicy shippingPolicy;

    private String code;
    private String name;
    private Integer rating;

    @OneToMany(mappedBy = "provider")
    private Set<PurchaseOrder> purchaseOrders ;

    @ManyToMany(mappedBy = "providers")
    private Set<Product> products ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShippingPolicy getShippingPolicy() {
		return shippingPolicy;
	}

	public void setShippingPolicy(ShippingPolicy shippingPolicy) {
		this.shippingPolicy = shippingPolicy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
    
}
