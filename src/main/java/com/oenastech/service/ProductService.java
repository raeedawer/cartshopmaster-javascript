package com.oenastech.service;

import com.oenastech.model.Product;
import com.oenastech.model.Provider;
import com.oenastech.repositary.ProductProvidersRepo;
import com.oenastech.repositary.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductProvidersRepo productProvidersRepo;

    public ProductService(ProductRepo productRepo, ProductProvidersRepo productProvidersRepo) {
        this.productRepo = productRepo;
        this.productProvidersRepo = productProvidersRepo;
    }
    public Float getPrice(Product product, Provider provider){

        return productProvidersRepo.findAllByProductAndProvider(product,provider);
    }
    public List<Product> searchForProducts(String name) {
        if (name != null) {
           
            List<Product> products = productRepo.findByName(name);
            if (products != null) {

                return products;
            }
        }
        return null;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product getReferenceById(Long id) {
        return productRepo.getReferenceById(id);
    }

    public List<Provider> findProviderByProductId(Long productId) {
        return productRepo.findProviderByProductId(productId);
    }

    public List<Product> findLastVisit(List<Long> list) {
        List<Product>products = new ArrayList<>();
        list.forEach(i->products.add(productRepo.getReferenceById(i)));
        return products;
    }
}
