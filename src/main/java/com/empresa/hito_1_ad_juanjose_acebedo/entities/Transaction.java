package com.empresa.hito_1_ad_juanjose_acebedo.entities;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String type; // "deposit" or "withdraw"
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}