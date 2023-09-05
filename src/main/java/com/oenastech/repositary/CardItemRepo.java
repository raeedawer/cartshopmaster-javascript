package com.oenastech.repositary;

import com.oenastech.model.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardItemRepo extends JpaRepository<CardItem,Long> {
    List<CardItem> findAllByClient(Long clientId);
}
