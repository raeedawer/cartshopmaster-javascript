package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {



        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "shipping_address")
        private String shippingAddress;

        @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<PurchaseOrder> purchaseOrders = new HashSet<>();
}
