package com.oenastech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
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
public class ProductProviders {



    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "providers_id")
    private Provider provider;

    private Float productPrice;

}
