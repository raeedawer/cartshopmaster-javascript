package com.oenastech.service;

import com.oenastech.model.Client;
import com.oenastech.model.Product;
import com.oenastech.model.VisitedItems;
import com.oenastech.repositary.VisitedItemsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class VisitedItemsService {

    private final VisitedItemsRepo visitedItemsRepo;
    private final ProductService productService;
    private final ClientService clientService;
    List<Long>list=new ArrayList<>();
    int fromIndex ,toIndex;
    private  Client client;
    @Autowired
    public VisitedItemsService(VisitedItemsRepo visitedItemsRepo,ProductService productService,ClientService clientService) {
        this.visitedItemsRepo = visitedItemsRepo;
        this.productService=productService;
        this.clientService=clientService;
    }

    @Transactional
    public void save(Product product){
        client=clientService.getCurrentClient();
        VisitedItems visitedItems=visitedItemsRepo
                .findByClientIdAndProductId(client.getId(),product.getId());
        if (visitedItems!=null) {

            visitedItemsRepo.delete(visitedItems);
        }

        visitedItems=new VisitedItems();
        visitedItems.setClientId(client.getId());
        visitedItems.setProductId(product.getId());
        visitedItems.setDate(new SimpleDateFormat().format(new Date()));
        visitedItemsRepo.save(visitedItems);

    }
    public List<Product> getVisitedItems() {
        fromIndex =list.size()-5;
        toIndex=list.size();
        client=clientService.getCurrentClient();
       list=visitedItemsRepo.findAllByClientIdOrderById(client.getId())
                .stream().map(VisitedItems::getProductId).toList();

        if (!list.isEmpty() ){
           if (list.size()>5){
          return   productService.findLastVisit(list.subList(fromIndex,toIndex));
           }
           return productService.findLastVisit(list);
        }
        List<Product> products=productService.findAll();

        return products.subList(products.size()-5,products.size());
    }
}
