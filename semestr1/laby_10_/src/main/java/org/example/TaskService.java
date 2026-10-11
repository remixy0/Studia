package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TaskService {
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    public  ObservableList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }




}
