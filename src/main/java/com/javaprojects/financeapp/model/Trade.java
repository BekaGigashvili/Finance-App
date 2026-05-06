package com.javaprojects.financeapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Trade {

    @Id
    @GeneratedValue
    private Long id;

    private String symbol;
    private int quantity;
    private double price;
}
