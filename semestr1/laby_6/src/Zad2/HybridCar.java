package Zad2;

import Zad1.Car;
import Zad1.Vehicle;

public class HybridCar extends Car {
    int batteryLevel = 45;
    int fuelLevel = 22;

    public HybridCar(String brand, int year) {
        super(brand, year);
    }

    @Override
    public double getFuelConsumption() {
        return batteryLevel + fuelLevel;
    }


}
