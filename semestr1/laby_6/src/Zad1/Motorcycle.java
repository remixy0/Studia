package Zad1;

import Zad3.Service;

public class Motorcycle extends Vehicle implements Service {
    public Motorcycle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    @Override
    public double getFuelConsumption() {
        return 50;
    }

    @Override
    public void service(){
        System.out.println("Smarowanie lancucha");
    }
}
