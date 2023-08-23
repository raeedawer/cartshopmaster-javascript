package com.oenastech.controller;

import com.oenastech.model.Product;
import com.oenastech.model.Provider;
import com.oenastech.repositary.ProductRepo;
import com.oenastech.repositary.ProviderRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


    ProductRepo productRepo;

    ProviderRepo providerRepo;


    @GetMapping("/search")
    public String getearch() {

        return "redirect:/home";
    }

    @Autowired
    public HomeController(ProductRepo productRepo, ProviderRepo providerRepo) {
        this.productRepo = productRepo;
        this.providerRepo = providerRepo;
    }

    @GetMapping("/home")
    public String productDetails(Model model) {

        List<Product> products = productRepo.findAll();
        List<Provider> providers = providerRepo.findAll();

        model.addAttribute("products", products);

        model.addAttribute("providers", providers);

        return "home";
    }

    @PostMapping("/search")
    public String searchResult(Model model, HttpServletRequest request) {
        String name = request.getParameter("keyword");
        if (name != null) {
            System.out.println("name is true =" + name);
            List<Product> products = productRepo.findByName(name);

            if (products != null) {
                products.forEach(System.out::println);
                model.addAttribute("products", products);
            }
        }

        return "home";
    }


    @PostMapping("/productDetails")
    public String productDetails(Model model, HttpServletRequest request) {

        Long id = Long.valueOf(request.getParameter("idProduct"));
        Product product = productRepo.getReferenceById(id);
        model.addAttribute("product", product);

        return "home";
    }
}
