package org.poo.cb;

public class StockFactory extends ItemFactory {
    @Override
    Stock createItem(String name) {
        return new Stock(name);
    }
}
