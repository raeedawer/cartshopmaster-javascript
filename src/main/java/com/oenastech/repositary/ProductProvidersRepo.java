package com.oenastech.repositary;

import com.oenastech.model.Product;
import com.oenastech.model.ProductProviders;
import com.oenastech.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductProvidersRepo extends JpaRepository<ProductProviders,Long> {
    @Query(value = "select p.productPrice from ProductProviders p where p.product=?1 and p.provider=?2")
    Float findAllByProductAndProvider(Product product, Provider provider);
}
