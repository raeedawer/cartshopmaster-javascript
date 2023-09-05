package com.oenastech.dto;

import com.oenastech.model.Role;
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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {



    private String firstName;


    private String lastName;


    private String email;


    private String password;


    private String shippingAddress;

    private int active;


    private Set<Role> roles;

}
