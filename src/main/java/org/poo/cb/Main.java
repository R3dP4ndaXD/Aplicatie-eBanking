package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }
        AppFacade app = AppFacade.getInstance(new File("src/main/resources/" + args[0]), new File("src/main/resources/" + args[1]));
        File commands = new File("src/main/resources/" + args[2]);
        try {
            Scanner s = new Scanner(commands);

            while (s.hasNextLine()) {
                List<String> command = Arrays.asList(s.nextLine().split(" ", 6));
                switch (command.get(0)) {
                    case "CREATE":
                        app.createUser(command.get(2), command.get(3), command.get(4), command.get(5));
                        break;
                    case "ADD":
                        switch (command.get(1)) {
                            case "FRIEND":
                                app.addFriend(command.get(2), command.get(3));
                                break;
                            case "ACCOUNT":
                                app.addAccount(command.get(2), command.get(3));
                                break;
                            case "MONEY":
                                app.addMoney(command.get(2), command.get(3), Double.parseDouble(command.get(4)));
                                break;
                        }
                        break;
                    case "EXCHANGE":
                        switch (command.get(1)) {
                            case "MONEY":
                                app.exchangeMoney(command.get(2), command.get(3), command.get(4), Double.parseDouble(command.get(5)));
                                break;
                            //ex:case "CRYPTO":
                        }
                        break;
                    case "TRANSFER":
                        switch (command.get(1)) {
                            case "MONEY":
                                app.transferMoney(command.get(2), command.get(3), command.get(4), Double.parseDouble(command.get(5)));
                                break;
                            //ex:case "CRYPTO":
                        }
                        break;
                    case "BUY":
                        switch (command.get(1)) {
                            case "STOCKS":
                                app.buyStocks(command.get(2), command.get(3), Integer.parseInt(command.get(4)));
                                break;
                            case "PREMIUM":
                                app.buyPremium(command.get(2));
                                break;
                            //ex:case "CRYPTO":
                        }
                    case "LIST":
                        switch (command.get(1)) {
                            case "USER":
                                app.listUser(command.get(2));
                                break;
                            case "PORTFOLIO":
                                app.listPortfolio(command.get(2));
                                break;
                        }
                        break;
                    case "RECOMMEND":
                        app.recommendStocks();
                        break;
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        AppFacade.deleteApp();
    }
}