package org.poo.cb;

import java.util.Iterator;
import java.util.List;

public class Account implements Item {
    private String currencyName;
    private Double amount = 0d;

    public Account(String currencyName) {
        this.currencyName = currencyName;
    }
    public String getName() {return currencyName;}
    @Override
    public Double getAmount() {return amount;}
    public void addAmount(Double amount) {
        this.amount += amount;
    }
    public void subAmount(Double amount) {
        this.amount -= amount;
    }


}


