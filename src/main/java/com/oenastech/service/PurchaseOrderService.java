package com.oenastech.service;

import com.oenastech.model.CardItem;
import com.oenastech.model.Client;
import com.oenastech.model.Provider;
import com.oenastech.model.PurchaseOrder;
import com.oenastech.repositary.PurchaseOrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


/**
 * <h2>An online store project <h2/>
 * <p> That displays products with different providers,
 * with a shopping cart and an order page.<p/>
 *
 * @author Raeed Awer
 * @since 1.1
 */
@Service
public class PurchaseOrderService {
    PurchaseOrderRepo purchaseOrderRepo;

    ClientService clientService;

    ProductService productService;
    ProviderService providerService;

    public PurchaseOrderService(
            PurchaseOrderRepo purchaseOrderRepo,
            ClientService clientService,
            ProductService productService,
            ProviderService providerService
    ) {
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.clientService = clientService;
        this.productService = productService;
        this.providerService = providerService;
    }

    @Transactional
    public void addOrder(List<CardItem> cardItems) {


        Map<Long, List<CardItem>> map = cardItems.stream().collect(Collectors.groupingBy(CardItem::getProvider));

        map.forEach((l, o) -> {
            PurchaseOrder order = new PurchaseOrder();
            Client client = clientService.getCurrentClient();
           AtomicReference<Float> price= new AtomicReference<>(0F);
            o.forEach(i ->{
                price.set(price.get() + i.getTotalPrice());
                i.setOrderId(order);
            });
            order.setCartItem(o);
            order.setShippingAddress(client.getShippingAddress());
            order.setClient(client);
            order.setProvider(providerService.getReferenceById(l));
            order.setShippingDate(new SimpleDateFormat().format(new Date()));
            order.setTotalAmount(price.get());
            purchaseOrderRepo.save(order);
        });

    }


    public List<PurchaseOrder> findAllByClient() {

        return purchaseOrderRepo.getAllOrdersByClient(clientService.getCurrentClient());


    }
}
