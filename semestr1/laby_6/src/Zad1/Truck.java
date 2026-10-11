package Zad1;

import Zad3.Service;

public class Truck extends Vehicle implements Service {
    public Truck(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }


    @Override
    public double getFuelConsumption() {
        return 602;
    }

    @Override
    public void service(){
        System.out.println("Kontrola hamulców");
    }
}
