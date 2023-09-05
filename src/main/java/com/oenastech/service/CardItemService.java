package com.oenastech.service;

import com.oenastech.dto.ItemDetails;
import com.oenastech.model.*;
import com.oenastech.repositary.CardItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


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
@Service
public class CardItemService {
    private final CardItemRepo cardItemRepo;
    private final ProductService productService;
    private final ClientService clientService;
    private final ProviderService providerService;

    @Autowired
    public CardItemService(
            CardItemRepo cardItemRepo,
            ProductService productService,
            ClientService clientService,
            ProviderService providerService) {
        this.cardItemRepo = cardItemRepo;
        this.productService=productService;
        this.clientService = clientService;
        this.providerService=providerService;
    }
    public List<CardItem> addCardItem(ItemDetails itemDetails){

        CardItem cardItem=new CardItem();
        System.out.println(itemDetails.getProviderId());
        Product product=productService.getReferenceById(itemDetails.getProductId());
        Provider provider=providerService.getReferenceById(itemDetails.getProviderId());
        Float price=productService.getPrice(product,provider);
        System.out.println(provider.getId());
        cardItem.setProduct(product);
        cardItem.setQuantity(itemDetails.getQuantity());
        cardItem.setPrice(price);
        cardItem.setTotalPrice(price*itemDetails.getQuantity());
        cardItem.setProvider(provider.getId());
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email  = principal.getUsername();
        Client client=clientService.findClientByEmail(email);
        System.out.println(client);
        cardItem.setClient(client.getId());
        cardItemRepo.save(cardItem);

        return this.findAllByClientId();
    }

    @Transactional
    public void removeCardItemFromClient(Long cardId){

        cardItemRepo.deleteById(cardId);
    }

    public List<CardItem> findAllByClientId() {
        List<CardItem> cardItems=cardItemRepo.findAllByClient(clientService.getCurrentClient().getId());
                cardItems.removeIf(i->i.getOrderId()!=null);
        return cardItems;
    }
}
