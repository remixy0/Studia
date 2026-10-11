package Zad1;

public abstract class Vehicle {
    String brand;
    int year;

    public abstract double getFuelConsumption();

    public void printInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
    }
}
