package org.example;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    List<Booking> bookings = new ArrayList<>();

    public void bookDevice(Employee employee, Device device){
        Booking booking = findBooking(employee);

        if (booking == null) {
            booking = new Booking(employee);
            bookings.add(booking);
        }

        booking.bookDevice(device);
    }

    public void relaseDevice(Employee employee, Device device){
        Booking booking = findBooking(employee);

        if (booking != null) {
            booking.relaseDevice(device);
        }
    }

    private Booking findBooking(Employee employee){
        for (Booking b : bookings){
            if (b.employee.equals(employee)){
                return b;
            }
        }
        return null;
    }

    public void showActiveBookings(){
        for (Booking b : bookings){
           b.showAllDevices();
        }

    }
}


class Booking{
    Employee employee;
    List<Device> devices = new ArrayList<>();

    public Booking(Employee employee){
        this.employee = employee;
    }

    void bookDevice(Device device){
        devices.add(device);
        device.rentDevice();
    }

    void relaseDevice(Device device){
        devices.remove(device);
        device.relaseDevice();
    }

    void showAllDevices(){
        employee.introduceYourself();
        for (Device d : devices){
            d.getDeviceInfo();
        }
        System.out.println("");
    }


}


class Employee{
    String name;
    String surname;
    String role;
    List<Device> devices = new ArrayList<>();


    public Employee(String name, String surname, String role){
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public void introduceYourself(){
        System.out.println(name + " " + surname + "  -" + role);
    }

}


class Device{
    String name;
    String manufacturer;
    String type;
    boolean isRented = false;

    public Device(String name, String manufacturer, String model){
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = model;
    }

    public void rentDevice(){
        this.isRented = true;
    }

    public void relaseDevice(){
        this.isRented = false;
    }

    public void getDeviceInfo(){
        System.out.println(this.name + " " + this.manufacturer + " " + this.type);
    }
}