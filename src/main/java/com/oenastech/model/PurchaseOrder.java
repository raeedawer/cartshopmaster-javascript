package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
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


    @OneToMany(mappedBy = "orderId")
    private List<CardItem> cartItem;
}
