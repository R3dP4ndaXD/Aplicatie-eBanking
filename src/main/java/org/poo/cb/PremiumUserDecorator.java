package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class PremiumUserDecorator extends UserDecorator {
    private List<String> recommendedStocks;
    public PremiumUserDecorator(User user, List<String> recommendedStocks) {
        super(user);
        this.recommendedStocks = recommendedStocks;
    }
    public String getEmail() {
        return delegate.getEmail();
    }

    public String getFirstname() {
        return delegate.getFirstname();
    }

    public String getLastname() {
        return delegate.getLastname();
    }

    public String getAddress() {
        return delegate.getAddress();
    }

    public ArrayList<String> getFriends() {
        return delegate.getFriends();
    }

    public void addFriend(String emailFriend) {
        delegate.addFriend(emailFriend);
    }
    public ItemCollection fetchPortfolio() {
        return delegate.fetchPortfolio();
    }
    public void addAccount(String currency) {
        delegate.addAccount(currency);
    }
    public void addMoney(String currency, Double amount) {
        delegate.addMoney(currency, amount);
    }
    public void transferMoney(User friend, String currency, double amount)  {
        delegate.transferMoney(friend, currency, amount);
    }
    public void buyStocks(String company, Integer noOfStocks, Double price) {
        if (recommendedStocks.contains(company)) {
            super.buyStocks(company, noOfStocks, 0.95 * price);
        } else {
            super.buyStocks(company, noOfStocks, price);
        }
    }
    public void exchange(String sourceCurrency, String destinationCurrency, Double amount, Double conversionRate) {
        if (amount * conversionRate > 0.5 * delegate.fetchPortfolio().getAccount(sourceCurrency).getAmount()) {
            super.exchange(sourceCurrency, destinationCurrency, amount, conversionRate * 100 / 101);
        } else {
            super.exchange(sourceCurrency, destinationCurrency, amount, conversionRate);
        }
    }
}
