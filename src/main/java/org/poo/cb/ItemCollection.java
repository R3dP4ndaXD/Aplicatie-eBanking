package org.poo.cb;

import java.util.Iterator;

public interface ItemCollection {
    public void addItem(Item c);
    public void removeItem(Item c);
    public Account getAccount(String currency);
    public Stock getStock(String company);
    public Iterator<Stock> getStockIterator();

    public Iterator<Account> getAccountIterator();
    public Iterator<Item> getItemIterator();
}
