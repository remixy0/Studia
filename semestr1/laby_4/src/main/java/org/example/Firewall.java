package org.example;


import java.util.ArrayList;
import java.util.List;

class Network{
    String subnetAddress;
    int numberOfDevices;
    Firewall firewall;

    Network(String subnetAddress, int numberOfDevices) {
        this.subnetAddress = subnetAddress;
        this.numberOfDevices = numberOfDevices;
    }

    public void addFirewall(Firewall firewall) {
        this.firewall = firewall;
    }

}


public class Firewall {
    List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void showRules() {
        for (Rule rule : rules) {
            rule.getInfo();
        }
    }

    public void filterConnection(String ip, int port) {
        for (Rule rule : rules) {
            if(rule.ipAddres == ip && rule.port == port){
                rule.getAction();
            }
        }

    }
}


class Rule {
    int port;
    String ipAddres;
    Action action;

    public Rule(int port, String ipAddress, Action action) {
        this.port = port;
        this.ipAddres = ipAddress;
        this.action = action;
    }

    public void getInfo(){
        System.out.println("Firewall Port: " + this.port);
        System.out.println("Firewall IP Address: " + this.ipAddres);
        System.out.println("Firewall Action: " + this.action);
        System.out.println("");
    }

    public Action getAction() {
        return action;
    }
}



enum Action{
    allow,deny
}