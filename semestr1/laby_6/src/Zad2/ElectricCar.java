package Zad2;

import Zad1.Car;
import Zad1.Vehicle;
import Zad3.Service;

public class ElectricCar extends Car implements Electric, Service {
    int batteryLevel = 77;
    public ElectricCar(String brand, int year) {
        super(brand, year);
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel ;
    }

    @Override
    public void service(){
        System.out.println("Kontrola Baterii");
    }
}