package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

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

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "shipping_amount")
    private Integer shippingAmount;

    @Column(name = "shipping_date")
    private String shippingDate;

    @Column(name = "shipping_address")
    private String shippingAddress;


    @OneToMany(mappedBy = "orderId",cascade = CascadeType.ALL)
    private List<CardItem> cartItem;


    
}
