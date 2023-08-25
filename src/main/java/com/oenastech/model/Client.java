package com.oenastech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {



        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        @Column(name = "active")
       private int active;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public Set<PurchaseOrder> getPurchaseOrders() {
			return purchaseOrders;
		}

		public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
			this.purchaseOrders = purchaseOrders;
		}

		public int getActive() {
			return active;
		}

		public void setActive(int active) {
			this.active = active;
		}
        
}

