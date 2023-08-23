package com.oenastech.repositary;

import com.oenastech.model.ShippingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingPolicyRepo extends JpaRepository<ShippingPolicy,Long> {
}
