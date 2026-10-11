package Zad1;

public class Product {
    String name;
    double price;
    category category;
    boolean discount;
    public Product(String name, double price, category category, boolean discount) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.discount = discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return name + ", " + price + ", " + category;
    }
}
