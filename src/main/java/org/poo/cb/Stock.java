package org.poo.cb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stock implements Item {
    private String stockName;
    private Integer amount = 0;

    public Stock(String stockName) {
        this.stockName = stockName;
    }
    public String getName() {return stockName;}
    public Number getAmount() {return amount;}
    public void addAmount(Integer amount) {
        this.amount += amount;
    }
    public void subAmount(Integer amount) {
        this.amount -= amount;
    }
}
