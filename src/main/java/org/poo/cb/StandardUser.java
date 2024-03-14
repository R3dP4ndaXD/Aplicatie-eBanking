package org.poo.cb;
import java.util.ArrayList;
import java.util.List;


public class StandardUser implements User {
    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private ItemCollection portfolio = new ItemCollectionImpl();
    private ArrayList<String> friends = new ArrayList<>();

    public StandardUser(String email, String firstname, String lastname, String address) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void addFriend(String emailFriend) {
        if (this.friends.contains(emailFriend)) {
            System.out.println("User with " + emailFriend + "is already a friend");
        } else {
            this.friends.add(emailFriend);
        }
    }

    public ItemCollection fetchPortfolio() {
        return portfolio;
    }
    public void addAccount(String currency) {
        AccountFactory accountFactory = new AccountFactory();
        if (portfolio.getAccount(currency) != null) {
            System.out.println("Account in currency " + currency + " already exists for user");
            return;
        }
        portfolio.addItem(accountFactory.createItem(currency));
    }

    public void addMoney(String currency, Double amount) {
        Account account = portfolio.getAccount(currency);
        account.addAmount(amount);
    }
    public void exchange(String sourceCurrency, String destinationCurrency, Double amount, Double conversionRate) {
        Account sourceAccount = portfolio.getAccount(sourceCurrency);
        Account destinationAccount = portfolio.getAccount(destinationCurrency);
        if (sourceAccount.getAmount() < amount * conversionRate) {
            System.out.println("Insufficient amount in account " + sourceCurrency +  " for exchange");
            return;
        }
        if (amount * conversionRate > 0.5 * (Double)sourceAccount.getAmount()) {
            sourceAccount.subAmount(amount * conversionRate * 1.01);
        } else {
            sourceAccount.subAmount(amount * conversionRate);
        }
        destinationAccount.addAmount(amount);
    }
    public void buyStocks(String company, Integer noOfStocks, Double price) {
        Stock stock = portfolio.getStock(company);
        Account account = portfolio.getAccount("USD");
        if (account.getAmount() < noOfStocks * price) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }
        stock.addAmount(noOfStocks);
        account.subAmount(noOfStocks * price);
    }
    public void transferMoney(User friend, String currency, double amount) {
        Account sourceAccount = portfolio.getAccount(currency);
        if (!friends.contains(friend.getEmail())) {
            System.out.println("You are not allowed to transfer money to " + friend.getEmail());
            return;
        }
        Account destinationAccount = friend.fetchPortfolio().getAccount(currency);
        if (sourceAccount.getAmount()< amount) {
            System.out.println("Insufficient amount in account " + currency + " for transfer");
            return;
        }
        sourceAccount.subAmount(amount);
        destinationAccount.addAmount(amount);
    }

}
