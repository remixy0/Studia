package org.example.Zad4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    static String path = "src/main/java/org/example/tasks_lib.json";
    static Gson gson = new Gson();
    public static void main(String[] args) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Creating new class","you have to create class",false));
        tasks.add(new Task("Fixing a method","you have to create method", false));
        tasks.add(new Task("Generating new field","you have to create field", false));
        tasks.add(new Task("Makig coffe","Black one",false));


        tasks.stream().forEach(x->{
            String classString = gson.toJson(x) + "\n";
            try {
                Files.write(Paths.get(path), classString.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


        List<Task> tasksRead = readFile(path);
        System.out.println(tasksRead);


    }

    public static List readFile(String fileName) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String fileString = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task user = gson.fromJson(line, Task.class);
            System.out.println(user);
            tasks.add(user);
        }
        return tasks;
    }



}
