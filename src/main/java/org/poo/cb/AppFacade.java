package org.poo.cb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class AppFacade {
    private static AppFacade INSTANCE;
    public static List<String> currencies = Arrays.asList("EUR", "GBP", "JPY", "CAD", "USD");
    public File exchangeRates;
    public  File stockValues;
    private static DecimalFormat df = new DecimalFormat("0.00");
    Map<String, User> users = new HashMap<>();
    private AppFacade(File exchangeRates, File stockValues) {
        this.exchangeRates = exchangeRates;
        this.stockValues = stockValues;
    }

    public static AppFacade getInstance(File exchangeRates, File stockValues) {
        if (INSTANCE == null) {
            INSTANCE = new AppFacade(exchangeRates, stockValues);
        }
        return INSTANCE;
    }
    public static void deleteApp() {
        INSTANCE = null;
    }
    public void createUser(String email, String firstname, String lastname, String address) {
        if (users.containsKey(email)) {
            System.out.println("User with " + email + " already exists");
        } else {
            users.put(email, new StandardUser(email, firstname, lastname, address));
        }
    }

    public void addFriend(String emailUser, String emailFriend) {
        User user = users.get(emailUser);
        User friend = users.get((emailFriend));
        if (user == null) {
            System.out.println("User with " + emailUser + " doesn't exist");
            return;
        }
        if (friend == null) {
            System.out.println("User with " + emailFriend + " doesn't exist");
            return;
        }
        user.addFriend(emailFriend);
        friend.addFriend(emailUser);    /*in teste am vazut ca afiseaza pt ambele persoane cand sunt deja prieteni,
                                          asa ca am lasat ca doua apeluri simple de adaugat in Arraylist, independente intre ele*/

    }
    public void addAccount(String email, String currency) {
        User user = users.get(email);
        if (user == null) {
            System.out.println("User with " + email + "doesn't exist");
            return;
        }
        user.addAccount(currency);

    }
    public void addMoney(String email, String currency, Double amount) {
        User user = users.get(email);
        user.addMoney(currency, amount);

    }
    public void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, Double amount) {
        User user = users.get(email);
        if (user == null) {
            System.out.println("User with " + email + "doesn't exist");
            return;
        }
        try {
            FileReader filereader = new FileReader(exchangeRates);
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> exchangeTable = csvReader.readAll();

            Double conversionRate = Double.parseDouble(exchangeTable.get(currencies.indexOf(destinationCurrency) + 1 )[currencies.indexOf(sourceCurrency) + 1]);
            user.exchange(sourceCurrency, destinationCurrency, amount, conversionRate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void transferMoney(String email, String friendEmail, String currency, Double amount) {
        User user = users.get(email);
        User friend = users.get(friendEmail);
        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        if (friend == null) {
            System.out.println("User with " + friendEmail + " doesn't exist");
            return;
        }
        user.transferMoney(friend, currency, amount);
    }

    public void buyStocks(String email, String company, Integer noOfStocks) {
        User user = users.get(email);
        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        try {
            FileReader filereader = new FileReader(stockValues);
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> stocksTable = csvReader.readAll();

            Double price = null;
            Iterator<String[]> iter = stocksTable.iterator();
            iter.next();
            while (iter.hasNext()) {
                String[] aux = iter.next();
                if (aux[0].equals(company)) {
                    price = Double.parseDouble(aux[10]);
                    break;
                }
            }
            user.buyStocks(company, noOfStocks, price);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listUser(String email) {
        User user = users.get(email);
        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        ObjectMapper Obj = new ObjectMapper();
        try {
            String jsonStr = Obj.writeValueAsString(user);
            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listPortfolio(String email) {
        User user = users.get(email);
        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        Iterator<Item> iter = user.fetchPortfolio().getItemIterator();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode itemsObject = mapper.createObjectNode();
        ArrayNode stocksArray= mapper.createArrayNode();
        ArrayNode accountsArray= mapper.createArrayNode();

        while (iter.hasNext()) {
            Item item = iter.next();
            if (item instanceof Stock) {
                Stock stock = (Stock)item;
                ObjectNode stockObject = mapper.createObjectNode();
                stockObject.put("stockName",stock.getName() );
                stockObject.put("amount",stock.getAmount().intValue());
                stocksArray.add(stockObject);
            } else if (item instanceof Account) {
                Account account = (Account)item;
                ObjectNode accountObject = mapper.createObjectNode();
                accountObject.put("currencyName",account.getName() );
                accountObject.put("amount", df.format(account.getAmount()));
                accountsArray.add(accountObject);
            }
        }
        itemsObject.set("stocks", stocksArray);
        itemsObject.set("accounts", accountsArray);
        System.out.println(itemsObject);
    }
    private ArrayList<String> SMAs() {
        ArrayList<String> recommended = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(stockValues);
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> stocksTable = csvReader.readAll();

            ObjectMapper mapper = new ObjectMapper();
            ArrayNode recommendedArray= mapper.createArrayNode();


            Iterator<String[]> iter1 = stocksTable.iterator();
            iter1.next();
            while (iter1.hasNext()) {
                String[] row = iter1.next();
                double shortSMA = 0;
                double longSMA = 0;
                Iterator<String> iter2 = Arrays.stream(row).iterator();
                String stock = iter2.next();
                int i = 1;
                while (iter2.hasNext()) {
                    double price = Double.parseDouble(iter2.next());
                    if (i > 5) {
                        shortSMA += price;
                    }
                    longSMA += price;
                    i++;
                }
                shortSMA /= 5;
                longSMA /= 10;
                if (shortSMA > longSMA) {
                    recommendedArray.add(stock);
                    recommended.add(stock);
                }
            }
            ObjectNode recommendedObject = mapper.createObjectNode();
            recommendedObject.set("stocksToBuy", recommendedArray);
            System.out.println(recommendedObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return recommended;
    }
    public void recommendStocks() {
        SMAs();
    }
    void buyPremium(String email) {
        User user = users.get(email);
        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        Account account = user.fetchPortfolio().getAccount("USD");
        if (account.getAmount() < 100d) {
            System.out.println("Insufficient amount in account for buying premium option");
            return;
        }
        account.subAmount(100d);

        PrintStream original = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        ArrayList<String> recommendations = SMAs();
        System.setOut(original);

        users.replace(email,new PremiumUserDecorator(user,recommendations));
    }

}//common/stockValues.csv/ testBonus/exchangeRates.csv testBonus/commands.txt
