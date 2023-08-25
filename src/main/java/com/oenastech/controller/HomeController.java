package com.oenastech.controller;

import com.oenastech.model.*;
import com.oenastech.repositary.*;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class HomeController {


    ProductRepo productRepo;

    ProviderRepo providerRepo;

    PurchaseOrderRepo purchaseOrderRepo;


    ClientRepo clientRepo;

    RolesRepo rolesRepo;
    EntityManager entityManager;
    @Autowired
    public HomeController(ProductRepo productRepo, ProviderRepo providerRepo, PurchaseOrderRepo purchaseOrderRepo, ClientRepo clientRepo,RolesRepo rolesRepo,EntityManager entityManager) {
        this.productRepo = productRepo;
        this.providerRepo = providerRepo;
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.clientRepo = clientRepo;
        this.rolesRepo=rolesRepo;
        this.entityManager=entityManager;
    }


    @GetMapping("/search")
    public String getSearch() {

        return "redirect:/home";
    }



    @GetMapping("/home")
    public String homePage(Model model) {

        List<Product> products = productRepo.findAll();


        model.addAttribute("products", products);


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

        Long productId = Long.valueOf(request.getParameter("productId"));
        Product product = productRepo.getReferenceById(productId);
        List<Provider> providers = productRepo.findProviderByProductId(productId);
        model.addAttribute("product", product);
        if (providers.isEmpty())
            System.out.println("yes");
        model.addAttribute("providers", providers);

        return "home";
    }

    @GetMapping("/")
    public String homeBackPage() {

        return "redirect:/home";
    }
    @GetMapping("/card")
    public String cardPage(HttpServletRequest request,Model model){

       List<CardItem> cardItems = (List<CardItem>) request.getSession().getAttribute("cardItems");

       model.addAttribute("cardItems",cardItems);
       return "home" ;
    }
    @GetMapping("/orders")
    public String cardPage(Model model){

        List<PurchaseOrder> orders=purchaseOrderRepo.findAll();
        model.addAttribute("orders",orders);

        return "home" ;
    }
    @PostMapping("/addCardItem")
    @Transactional
    public String addCardItem(Model model, HttpServletRequest request) {
        List<CardItem> cardItems;
        if (request.getSession().getAttribute("cardItems") == null) {
            cardItems = new ArrayList<>();
            request.getSession().setAttribute("cardItems", cardItems);
        }
        Long id = Long.valueOf(request.getParameter("productId"));
        Integer quantity = Integer.valueOf(request.getParameter("quantity"));
        Product product = productRepo.getReferenceById(id);
        CardItem cardItem = new CardItem();
        cardItem.setProduct(product);
        cardItem.setQuantity(quantity);
        cardItems = (List<CardItem>) request.getSession().getAttribute("cardItems");
        assert cardItems != null;
        cardItems.add(cardItem);
        model.addAttribute("cardItems", cardItems);

        return "home";
    }

    @PostMapping("/removeItem")
    public String removeItem(Model model, HttpServletRequest request) {

        List<CardItem> cardItems = (List<CardItem>) request.getSession().getAttribute("cardItems");


        if (cardItems == null)
            System.out.println("yes is null");
        Long id = Long.valueOf(request.getParameter("productId"));
        System.out.println("err1");
        cardItems.removeIf(cardItem -> Objects.equals(cardItem.getProduct().getId().intValue(), id.intValue()));
        if (!cardItems.isEmpty()) {

            System.out.println("err2");

            request.getSession().setAttribute("cardItems", cardItems);
            model.addAttribute("cardItems", cardItems);
        }

        return "home";
    }

    @PostMapping("/addOrder")
    @Transactional
    public String addOrder(Model model,HttpServletRequest request){
       PurchaseOrder order=new PurchaseOrder();

       List<CardItem> cardItems= (List<CardItem>) request.getSession().getAttribute("cardItems");
       order.setCartItem(cardItems);
        UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String email  = principal.getUsername();
        Client client=clientRepo.getClientByEmail(email);
       order.setClient(client);
       order.setProvider(providerRepo.getReferenceById(1L));
       order.setShippingDate(new SimpleDateFormat().format(new Date()));
       AtomicInteger totalprise= new AtomicInteger();
          cardItems.forEach(p->{  totalprise.addAndGet((int) (p.getQuantity() * p.getProduct().getPrecio())); });
       order.setTotalAmount(totalprise.floatValue());
       order.setShippingAmount( Integer.valueOf(""+cardItems.stream().map(p->p.getProduct().getPrecio()*p.getQuantity()).count())) ;
       order.setShippingAddress(client.getShippingAddress());
       purchaseOrderRepo.save(order);
       List<PurchaseOrder> orders=purchaseOrderRepo.findAll();
       model.addAttribute("orders",orders);
        request.getSession().setAttribute("cardItems",new ArrayList<CardItem>());
        return "home";
    }

    @PostMapping("/addClient")
    @Transactional
    public String addClient(HttpServletRequest request){

        String firstName,lastName ,pass,email,address;

        Client client=new Client();
        Roles role=new Roles();

       firstName = request.getParameter("firstName");
       lastName= request.getParameter("lastName");
       email= request.getParameter("email");
       pass= request.getParameter("password");
        address=request.getParameter("shippingAddress");

        if((firstName==null||lastName==null||email==null||pass==null||address==null)){
            return "register";
        }
        try {
         Client client1=   clientRepo.getClientByEmail(email);
        }catch (Exception e){

        }

        client.setPassword("{noop}"+pass);
        client.setEmail(email);
        client.setFirstName(firstName);
        client.setShippingAddress(address);
        client.setLastName(lastName);
        client.setActive(1);
        role.setRole("ROLE_user");
        role.setEmail(email);
         clientRepo.save(client);
         rolesRepo.save(role);
         System.out.println(client);

        return "login";
    }

}
