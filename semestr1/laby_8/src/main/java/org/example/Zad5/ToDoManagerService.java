package org.example.Zad5;

import com.google.gson.Gson;
import org.example.Zad4.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoManagerService {
    static String path = "src/main/java/org/example/tasks_lib.json";
    static Gson gson = new Gson();
    ToDoManagerRepository toDoManagerRepository;
    public ToDoManagerService(ToDoManagerRepository toDoManagerRepository) {
        this.toDoManagerRepository = toDoManagerRepository;
    }

    public void initializeDataForToDoManager() throws FileNotFoundException {
       toDoManagerRepository.addData(readFile(path));
    }

    public List readFile(String fileName) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task user = gson.fromJson(line, Task.class);
            tasks.add(user);
        }
        return tasks;
    }

    public void saveData() throws FileNotFoundException {
        toDoManagerRepository.tasks.stream().forEach(x -> {
            String classString = gson.toJson(x) + "\n";
            try {
                Files.write(Paths.get(path), classString.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
            String newBackupName = "tasks" + LocalDateTime.now() + ".json";
            String path = "src/main/java/org/example/backups/" + newBackupName;
            createFile(newBackupName);
            readFile("src/main/java/org/example/tasks_lib.json").stream().forEach(x -> {
                String classString = gson.toJson(x) + "\n";
                try {
                    Files.write(Paths.get(path), classString.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

    }

    public void createFile(String fileName) throws FileNotFoundException {
        String path = "src/main/java/org/example/backups/" + fileName;
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importBackup() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the backup file: ");
        String fileName = scanner.nextLine();
        List<Task> choosenBackup =  readFile(fileName);
        toDoManagerRepository.tasks = choosenBackup;
        saveData();
    }



    public static String checkDirectory() {
        Path folder = Paths.get("src/main/java/org/example/backups");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.json")) {

            for (Path entry : stream) {
                return entry.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readBackups() {
        Path folder = Paths.get("src/main/java/org/example/backups");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.json")) {
            for (Path entry : stream) {
                System.out.println(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
