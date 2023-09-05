package com.oenastech.repositary;

import com.oenastech.model.Client;
import com.oenastech.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder,Long> {
    List<PurchaseOrder> getAllOrdersByClient(Client client);
}
