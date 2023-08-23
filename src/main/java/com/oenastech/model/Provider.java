package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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
}
