package com.oenastech.repositary;

import com.oenastech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("SELECT p from Product p where p.name like %?1%  or p.code like %?1% ")
    List<Product> findByName(String name);
}
