package Zad1;

import Zad3.Service;

import java.io.Serializable;

public class Car extends Vehicle implements Service{
    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    @Override
    public double getFuelConsumption() {
        return 178;
    }

    public void service(){
        System.out.println("Wymiana oleju");
    }
}
