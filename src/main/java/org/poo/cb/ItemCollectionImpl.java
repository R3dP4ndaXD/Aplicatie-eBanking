package org.poo.cb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemCollectionImpl implements ItemCollection {
    private List<Item> itemsList;


    public ItemCollectionImpl() {
        itemsList = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.itemsList.add(item);
    }

    public void removeItem(Item item) {
        this.itemsList.remove(item);
    }

    public Iterator<Stock> getStockIterator() {
        return new StockIterator();
    }

    public Iterator<Account> getAccountIterator() {
        return new AccountIterator();
    }
    public Iterator<Item> getItemIterator() {
        return new ItemIterator();
    }

    public Account getAccount(String currency) {
        AccountIterator iter = new AccountIterator();
        while (iter.hasNext()) {
            Account aux = iter.next();
            if (aux.getName().equals(currency)) {
                return aux;
            }
        }
        return null;
    }
    public Stock getStock(String company) {
        StockIterator iter = new StockIterator();
        while (iter.hasNext()) {
            Stock aux = iter.next();
            if (aux.getName().equals(company)) {
                return aux;
            }
        }
        StockFactory stockFactory = new StockFactory();
        Stock stock = stockFactory.createItem(company);
        this.itemsList.add(stock);
        return stock;
    }

    private class ItemIterator implements Iterator<Item> {
        private int index = 0;

        public int getIndex() {
            return index;
        }

        public ItemIterator() {}

        public boolean hasNext() {
            return index < itemsList.size();
        }
        public Item next() {
            if (this.hasNext()) {
                return itemsList.get(index++);
            }
            return null;
        }
    }
    private class StockIterator implements Iterator<Stock> {
        private int index = 0;

        public StockIterator() {}

        public boolean hasNext() {
            while (index < itemsList.size()) {
                if (itemsList.get(index) instanceof Stock) {
                    return true;
                }
                index++;
            }
            return false;
        }
        public Stock next() {
            if (this.hasNext()) {
                return (Stock)itemsList.get(index++);
            }
            return null;
        }
    }
    private class AccountIterator implements Iterator<Account> {
        private int index = 0;

        public AccountIterator() {}
        @Override
        public boolean hasNext() {
            while (index < itemsList.size()) {
                if (itemsList.get(index) instanceof Account) {
                    return true;
                }
                index++;
            }
            return false;
        }
        @Override
        public Account next() {
            if (this.hasNext()) {
                return (Account) itemsList.get(index++);
            }
            return null;
        }
    }

}
