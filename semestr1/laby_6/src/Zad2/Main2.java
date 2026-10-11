package Zad2;

import Zad1.Car;
import Zad1.Vehicle;

public class Main2 {
    public static void main(String[] args) {
        Vehicle [] lista = new Vehicle[3];
        lista[0] = new ElectricCar("Tesla", 2020);
        lista[1] = new HybridCar("Toyota", 2018);
        lista[2] = new ElectricCar("BMW", 2021);

        for(Vehicle v : lista){
            System.out.println(v.getFuelConsumption());
            v.printInfo();
            if(v instanceof Electric){
                System.out.println(((Electric) v).getBatteryLevel());
            }
        }
    }
}
