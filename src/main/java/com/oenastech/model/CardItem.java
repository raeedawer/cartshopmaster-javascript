package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CardItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private PurchaseOrder orderId;


	@Column(name = "quantity")
    private Integer quantity;


    @Column(name = "provider_id")
    private Long provider;

    @Column(name = "client_id")
    private Long client;

    @Column(name = "item_price")
    private Float price;


    @Column(name = "total_price")
    private Float totalPrice;


}

