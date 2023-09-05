package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

/**
 * <h2>An online store project <h2/>
 * <p> That displays products with different providers,
 *with a shopping cart and an order page.<p/>
 *
 *
 *
 *
 * @author Raeed Awer
 *
 *@since 1.1
 */
@Getter
@Setter
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

    
}
