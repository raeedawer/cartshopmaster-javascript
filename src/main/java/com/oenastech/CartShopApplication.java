package com.oenastech;

import com.oenastech.repositary.ProductProvidersRepo;
import com.oenastech.repositary.ProductRepo;
import com.oenastech.repositary.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * <h2>An online store project <h2/>
 * <p> That displays products with different providers,
 *with a shopping cart and an order page.<p/>

 * @author Raeed Awer
 *
 *@since 1.1
 */
@SpringBootApplication
public class CartShopApplication {
    private static    ProductProvidersRepo repo;
    private static ProviderRepo providerRepo;
    private static ProductRepo productRepo;

    public CartShopApplication() {
    }

    @Autowired
    public CartShopApplication(ProductProvidersRepo repo, ProviderRepo providerRepo, ProductRepo productRepo) {
        CartShopApplication.repo = repo;
        CartShopApplication.providerRepo = providerRepo;
        CartShopApplication.productRepo = productRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(CartShopApplication.class, args);

        System.out.println(repo
                .findAllByProductAndProvider
                        (productRepo.getReferenceById(1L),
                                providerRepo.getReferenceById(3L)));
    }

}
