package com.javaprojects.financeapp.repository;

import com.javaprojects.financeapp.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    
}
