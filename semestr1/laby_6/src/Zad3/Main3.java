package Zad3;

import Zad1.Car;
import Zad1.Motorcycle;
import Zad1.Truck;
import Zad1.Vehicle;
import Zad2.ElectricCar;

public class Main3 {
    public static void main(String[] args) {
        Vehicle [] pojazdy;
        pojazdy = new Vehicle[0];

        pojazdy[0] = new Car("Toyota",2000);
        pojazdy[1] = new Car("BMW", 2012);
        pojazdy[2] = new ElectricCar("");
        pojazdy[3] = new ElectricCar();
        pojazdy[4] = new Truck();
        pojazdy[5] = new Motorcycle();

    }
}
