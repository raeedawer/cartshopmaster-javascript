package com.oenastech.controller;

import com.oenastech.dto.ClientDto;
import com.oenastech.dto.ItemDetails;
import com.oenastech.model.*;
import com.oenastech.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


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
public class HomeController implements ErrorController {


    ProductService productService;

    ProviderService providerService;

    PurchaseOrderService purchaseOrderService;


    ClientService clientService;

    CardItemService cardItemService;

    VisitedItemsService visitedItemsService;

    @Autowired
    public HomeController(
            ProductService productService,
            ProviderService providerService,
            PurchaseOrderService purchaseOrderService,
            ClientService clientService,
            CardItemService cardItemService,
            VisitedItemsService visitedItemsService) {
        this.productService = productService;
        this.providerService = providerService;
        this.purchaseOrderService = purchaseOrderService;
        this.clientService = clientService;
        this.cardItemService = cardItemService;
        this.visitedItemsService = visitedItemsService;

    }
    @GetMapping({"/home"})
    public String homePage(Model model) {

        List<Product> products = visitedItemsService.getVisitedItems();
        model.addAttribute("products", products);

        return "home";
    }
    @PostMapping("/search")
    public String searchResult(@RequestParam("keyword") String name, Model model) {

        List<Product> products = productService.searchForProducts(name);

        model.addAttribute("products", products);

        return "home";
    }
    @PostMapping("/productDetails")
    public String productDetails(@RequestParam("productId") Long productId, Model model) {

        Product product = productService.getReferenceById(productId);
        visitedItemsService.save(product);
        Map<Provider,Float> prices= new HashMap<>();
        model.addAttribute("product", product);
        List<Provider> providers = productService.findProviderByProductId(productId);
        providers.forEach(i->prices.put(i,productService.getPrice(product,i)));
        Float minPrice= Collections.min(prices.values());
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("prices",prices);
        model.addAttribute("providers", providers);

        return "home";
    }
    @GetMapping("/card")
    public String cardPage(Model model) {

        Object cardItems = cardItemService.findAllByClientId();
        System.out.println(cardItems);

        model.addAttribute("cardItems", cardItems);
        return "home";
    }
    @GetMapping("/orders")
    public String ordersPage(Model model) {

        List<PurchaseOrder> orders = purchaseOrderService.findAllByClient();

        model.addAttribute("orders", orders);

        return "home";
    }

    @PostMapping("/addCardItem")
    public String addCardItem(@ModelAttribute ItemDetails itemDetails, Model model) {

        List<CardItem> cardItems = cardItemService.addCardItem(itemDetails);

        model.addAttribute("cardItems", cardItems);
        return "home";
    }

    @PostMapping("/removeItem")
    public String removeItem(@RequestParam Long cardId, Model model) {

        cardItemService.removeCardItemFromClient(cardId);

        model.addAttribute("cardItems", cardItemService.findAllByClientId());

        return "home";
    }
    @PostMapping("/addOrder")
    @Transactional
    public String addOrder( Model model) {

        List<CardItem> cardItems=cardItemService.findAllByClientId();
        purchaseOrderService.addOrder(cardItems);
        List<PurchaseOrder> orders = purchaseOrderService.findAllByClient();
        model.addAttribute("orders", orders);
        return "home";
    }
    @GetMapping("/error")
    public String errorPage() {

        return "redirect:/home";
    }
    @PostMapping("/addClient")
    public String addClient(@ModelAttribute("client") ClientDto clientDto) {

        clientService.addClient(clientDto);

        return "login";
    }

}
