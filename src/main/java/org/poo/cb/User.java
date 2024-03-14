package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public interface User {
    public String getEmail();

    public String getFirstname();

    public String getLastname();

    public String getAddress();

    public ArrayList<String> getFriends();

    public void addFriend(String emailFriend);
    public ItemCollection fetchPortfolio();
    public void addAccount(String currency);
    public void addMoney(String currency, Double amount);
    public void transferMoney(User friend, String currency, double amount);
    public void exchange(String sourceCurrency, String destinationCurrency, Double amount, Double conversionRate);
    public void buyStocks(String company, Integer noOfStocks, Double price);

}
