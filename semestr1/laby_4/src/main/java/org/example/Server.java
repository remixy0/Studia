package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Server {
    int IPAddres, RAM, numberOfCores;
    List<HardDrive> hardDrives = new ArrayList<>();

    public Server() {
        this.IPAddres = 11111111;
        this.RAM = 64;
        this.numberOfCores = 16;
    }

    public Server(int IPAddres, int RAM, int numberOfCores) {
        this.IPAddres = IPAddres;
        this.RAM = RAM;
        this.numberOfCores = numberOfCores;
    }

    public void addHardDrive(HardDrive hardDrive) {
        this.hardDrives.add(hardDrive);
    }

    public void showHardwareInfo(){
        System.out.println("");
        System.out.println("IP Address: " + this.IPAddres);
        System.out.println("RAM: " + this.RAM);
        System.out.println("Number of Cores: " + this.numberOfCores);
        for (int i = 0; i<this.hardDrives.size(); i++) {
            System.out.println("");
            System.out.println("Drive " + (i+1)+ ":");
            hardDrives.get(i).showHardwareInfo();
        }
    }

}


class HardDrive{
    int storageSpace;
    int speed;
    String type;

    public HardDrive() {
        this.storageSpace = 1024;
        this.speed = 2000;
        this.type = "SSD";
    }

    public HardDrive(int storageSpace, int speed, String type) {
        this.storageSpace = storageSpace;
        this.speed = speed;
        this.type = type;
    }

    public void showHardwareInfo(){
        System.out.println("Storage Space: " + this.storageSpace);
        System.out.println("Speed: " + this.speed);
        System.out.println("Type: " + this.type);
    }



}