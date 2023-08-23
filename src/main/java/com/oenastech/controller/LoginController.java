package com.oenastech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String register(){


        return "register";
    }


}
