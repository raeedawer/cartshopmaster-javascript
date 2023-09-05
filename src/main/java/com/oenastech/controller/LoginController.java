package com.oenastech.controller;

import com.oenastech.dto.ClientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){


        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){

        return "access_denied" ;
    }



    @GetMapping("/register")
    public String register(Model model){

        ClientDto client=new ClientDto();
        model.addAttribute("client",client);


        return "register";
    }


}
