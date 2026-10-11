package Zad6;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main6 {
    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<>();
        Deque<Order> orders = new LinkedList<>();
        Item item1 = new Item("pencil", 10);
        Item item2 = new Item("backpack", 4);
        Item item3 = new Item("shoes", 5);

        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

        orders.add(new Order(item1.itemId, 7));
        orders.add(new Order(item2.itemId, 3));
        orders.add(new Order(item3.itemId, 10));


//        for (int i = 0; i <= orders.size(); i++) {
//            Order order = orders.poll();
//            inventory.stream().filter(x-> x.itemId == order.itemId).forEach(x ->{
//                if(x.quantity >= order.amount) {
//                    x.quantity -= order.amount;
//                    System.out.println("made an order for  "+ x.name + "   " + order.amount+ "pcs");
//                }else{
//                    System.out.println("couldnt made and  order for  " + x.name + "   " + order.amount + "pcs, there is only "+ x.quantity +"pcs in the inventory");
//                    orders.offerLast(order);
//                }
//            });
//        };


        int initialSize = orders.size();
        Order lastOrder = null;
        for (int i = 0; i < initialSize; i++) {

            Order order = orders.poll();
            if(order == lastOrder){
                break;
            }

            lastOrder = order;
            if (order == null) break;

            boolean processed = false;

            for (Item x : inventory) {
                if (x.itemId == order.itemId) {

                    if (x.quantity >= order.amount) {
                        x.quantity -= order.amount;
                        System.out.println("Zrealizowano zamówienie na: " + x.name + " (" + order.amount + " szt.)");
                        processed = true;
                    } else {
                        System.out.println("Brak towaru: " + x.name + ". Wymagane: " + order.amount + ", Dostępne: " + x.quantity);
                        orders.offerLast(order);
                    }
                    break;
                }
            }

            if (!processed) {
                orders.offerLast(order);
            }
        }






    }
}
