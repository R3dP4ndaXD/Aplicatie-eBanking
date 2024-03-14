package org.poo.cb;

public class AccountFactory extends ItemFactory {
    @Override
    Account createItem(String name) {
        return new Account(name);
    }
}
