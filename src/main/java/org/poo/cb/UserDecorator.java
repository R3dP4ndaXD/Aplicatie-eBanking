package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public abstract class UserDecorator implements User {
    protected User delegate;
    public UserDecorator(User user) {
        this.delegate = user;
    }
    public void buyStocks(String company, Integer noOfStocks, Double price) {
        delegate.buyStocks(company, noOfStocks, price);
    }
    public void exchange(String sourceCurrency, String destinationCurrency, Double amount, Double conversionRate) {
        delegate.exchange(sourceCurrency, destinationCurrency, amount, conversionRate);
    }

}
