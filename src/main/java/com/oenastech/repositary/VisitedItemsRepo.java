package com.oenastech.repositary;

import com.oenastech.model.VisitedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VisitedItemsRepo extends JpaRepository<VisitedItems,Long> {
    List<VisitedItems> findAllByClientIdOrderById(Long clientId);
    VisitedItems findByClientIdAndProductId(Long clientId,Long productId);

}
