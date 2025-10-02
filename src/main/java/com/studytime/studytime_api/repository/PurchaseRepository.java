package com.studytime.studytime_api.repository;

import com.studytime.studytime_api.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository <Purchase, Long> {
    
}
