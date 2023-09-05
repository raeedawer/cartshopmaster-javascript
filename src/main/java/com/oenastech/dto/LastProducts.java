package com.oenastech.dto;

import com.oenastech.model.Product;
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
import java.util.List;
@Setter
@Getter
public class LastProducts {

    String name;
    List<Product> products;
}
