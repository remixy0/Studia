package org.example;

import java.util.ArrayList;
import java.util.List;

public class WebApplication {
    String name;
    String version;
    boolean status;
    List<Module> modules = new ArrayList<>();


    public WebApplication(String name, String version, boolean status) {
        this.name = name;
        this.version = version;
        this.status = status;
    }


    public void addModule(Module module) {
        modules.add(module);
    }

    public void assignDeveloperToModule(Module module, Developer2 developer) {
        module.assignDeveloper(developer);
    }

    public void showModules() {
        for (Module module : modules) {
            module.getInfo();
        }
    }


}

class Module{
    String name;
    String description;
    String version;
    Developer2 developer;

    public Module(String name, String description, String version) {
        this.name = name;
        this.description = description;
        this.version = version;
    }


    public void assignDeveloper(Developer2 developer) {
        this.developer = developer;
    }

    public void getInfo(){
        System.out.println(name + " " + description + " " + version);
        developer.getInfo();
        System.out.println("");
    }

}

class Developer2{
    String name;
    String role;
    Module module;

    public Developer2(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void getInfo(){
        System.out.println("Developer: " + name + " " + role);
    }


}

