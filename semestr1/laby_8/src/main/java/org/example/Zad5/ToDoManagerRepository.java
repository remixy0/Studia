package org.example.Zad5;

import com.google.gson.Gson;
import org.example.Zad4.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ToDoManagerRepository {
    List<Task> tasks = new ArrayList<>();
    static String path = "src/main/java/org/example/tasks_lib.json";
    static Gson gson = new Gson();


    public void addData(List<Task> data){
        tasks.addAll(data);
    }

    public void addTask(Task data){
        tasks.add(data);
    }


}
