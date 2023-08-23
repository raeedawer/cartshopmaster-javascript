package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
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
}
