package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shipping_policy")
@NoArgsConstructor
@AllArgsConstructor
public class ShippingPolicy {



    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "provider_id")
    private Integer providerId;

    @Column(name = "min_range")
    private Integer minRange;

    @Column(name = "max_range")
    private Integer maxRange;

    @Column(name = "price")
    private Integer price;

    @Column(name = "free_shipping_threshold")
    private Integer freeShippingThreshold;

    @OneToMany(mappedBy = "shippingPolicy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Provider> providers = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getMinRange() {
		return minRange;
	}

	public void setMinRange(Integer minRange) {
		this.minRange = minRange;
	}

	public Integer getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(Integer maxRange) {
		this.maxRange = maxRange;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getFreeShippingThreshold() {
		return freeShippingThreshold;
	}

	public void setFreeShippingThreshold(Integer freeShippingThreshold) {
		this.freeShippingThreshold = freeShippingThreshold;
	}

	public Set<Provider> getProviders() {
		return providers;
	}

	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}
    
}
