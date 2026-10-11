package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {
    String name;
    String location;
    int numberOfServers;
    List<Server2> servers = new ArrayList<>();
    List<NetworkSwitch> switches = new ArrayList<>();

    public DataCenter(){
        this.name="DataCenter";
        this.location="Wrocław";
        this.numberOfServers=0;
    }

    public DataCenter(String name,String location,int numberOfServers){
        this.name=name;
        this.location=location;
        this.numberOfServers=numberOfServers;
    }

    public void addServer(Server2 server){
        servers.add(server);
        numberOfServers++;
    }

    public void addSwitch(NetworkSwitch networkSwitch){
        switches.add(networkSwitch);
    }

    public void showInfrasctructure(){
        System.out.println("Name: "+this.name);
        System.out.println("Location: "+this.location);

        System.out.println("Servers:");
        for(Server2 server:servers) {
            server.getInfo();
        }

        System.out.println("Switches:");
        for(NetworkSwitch networkSwitch:switches){
            networkSwitch.getInfo();
        }

    }


}


class Server2{
    String name;
    String ipAddress;
    int threads;


    public Server2(){
        this.name = "Server";
        this.ipAddress = "192.168.1.1";
        this.threads =  64;
    }

    public Server2(String name,String ipAddress,int threads){
        this.name = name;
        this.ipAddress = ipAddress;
        this.threads = threads;
    }

    public void getInfo(){
        System.out.println("Name: "+this.name);
        System.out.println("IP Address: "+this.ipAddress);
        System.out.println("Threads: "+this.threads);
        System.out.println("");

    }
}


class NetworkSwitch{
    String model;
    int numberOfPorts;

    public NetworkSwitch(){
        this.model = "TPlink";
        this.numberOfPorts = 20;
    }
    public NetworkSwitch(String model,int numberOfPorts){
        this.model = model;
        this.numberOfPorts = numberOfPorts;
    }

    public void getInfo(){
        System.out.println("Model: "+this.model);
        System.out.println("Number of Ports: "+this.numberOfPorts);
        System.out.println("");
    }


}