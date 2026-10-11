package Zad6;

import java.util.UUID;

public class Item {
    UUID itemId;
    String name;
    int quantity;

    public Item(String name, int quantity) {
        this.itemId = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
    }
}
