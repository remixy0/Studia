package Zad6;

import java.util.UUID;

public class Order {
    UUID orderId;
    UUID itemId;
    int amount;
    public Order(UUID itemId, int amount) {
        this.orderId = UUID.randomUUID();
        this.itemId = itemId;
        this.amount = amount;
    }

}
