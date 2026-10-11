package org.example.Zad5;

import com.google.gson.Gson;

import java.io.FileNotFoundException;

public class ToDoManager {
    public static void main(String[] args) throws FileNotFoundException {
        ToDoManagerUI toDoManager = new ToDoManagerUI(new ToDoManagerService(new ToDoManagerRepository()));
        while (toDoManager.showOptions()) {
            System.out.println("");
        }


    }
}
