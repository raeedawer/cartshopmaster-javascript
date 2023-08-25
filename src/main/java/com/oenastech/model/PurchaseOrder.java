package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "purchase_order")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Column(name = "total_amount", precision = 12, scale = 0)
    private Float totalAmount;

    @Column(name = "shipping_amount")
    private Integer shippingAmount;

    @Column(name = "shipping_date", length = 10)
    private String shippingDate;

    @Column(name = "shipping_address")
    private String shippingAddress;


    @OneToMany(mappedBy = "orderId",cascade = CascadeType.ALL)
    private List<CardItem> cartItem;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Provider getProvider() {
		return provider;
	}


	public void setProvider(Provider provider) {
		this.provider = provider;
	}


	public Float getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Integer getShippingAmount() {
		return shippingAmount;
	}


	public void setShippingAmount(Integer shippingAmount) {
		this.shippingAmount = shippingAmount;
	}


	public String getShippingDate() {
		return shippingDate;
	}


	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public List<CardItem> getCartItem() {
		return cartItem;
	}


	public void setCartItem(List<CardItem> cartItem) {
		this.cartItem = cartItem;
	}
    
}
