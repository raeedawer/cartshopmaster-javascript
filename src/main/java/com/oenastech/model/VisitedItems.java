package com.oenastech.model;

import jakarta.persistence.*;
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
@Table(name = "visited_items")
public class VisitedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long clientId;

    Long productId;

    String date;

}
