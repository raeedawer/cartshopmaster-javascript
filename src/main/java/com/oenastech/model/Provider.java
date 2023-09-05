package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
