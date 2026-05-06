package com.javaprojects.financeapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    private String symbol;
    private int totalQuantity;
    private double avgPrice;
    private double pnl;
}
