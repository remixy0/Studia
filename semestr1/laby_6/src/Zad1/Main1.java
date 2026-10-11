package Zad1;

import java.util.Vector;

public class Main1 {
    public static void main(String[] args) {
        Vehicle[] lista = new Vehicle[6];
        lista[0] = new Car("Skoda",2004);
        lista[1] = new Car("Ford",2005);
        lista[2] = new Truck("Chevy",2000);
        lista[3] = new Truck("BMW",2012);
        lista[4] = new Motorcycle("HarleyDavidson",2020);
        lista[5] = new Motorcycle("KTM",2016);

        for(Vehicle v : lista){
            v.printInfo();
            System.out.println(v.getFuelConsumption());
        }
    }
}
