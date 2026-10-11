package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    Scene ekranDodawania, ekranGlowny, ekranInfo;
    TaskService taskService = new TaskService();
    ListView<Task> taskList = new ListView<>(taskService.getTasks());
    Task chosenTask;

    @Override
    public void start(Stage stage) {
        taskService.addTask(new Task("New App","App about your hobbies", Priority.MEDIUM));
        taskService.addTask(new Task("Vaccuming","I have to vaccum my room and a bathroom", Priority.HIGH));
        taskService.addTask(new Task("Math homework","I have to do my math homework", Priority.MEDIUM));

        taskList.setCellFactory(param -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.name +" - "+ item.description);

                    if (item.isDone()){
                        setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-text-fill: black; -fx-font-weight: bold");
                    }
                }
            }
        });


        Label label_info_name = new Label("name");
        label_info_name.getStyleClass().add("duzy-tekst");
        Label label_info_description = new Label("description");
        Label label_info_priority = new Label("priority");


        Label etykietaInfo = new Label("");
        etykietaInfo.getStyleClass().add("duzy-tekst");

        Button dodaj_zadanie = new Button("Add new task");
        dodaj_zadanie.getStyleClass().add("button-green");

        Button usun_zadanie = new Button("Remove task");
        usun_zadanie.getStyleClass().add("button-red");

        Button info_zadanie = new Button("Get more info");
        info_zadanie.getStyleClass().add("button-blue");

        taskList.getSelectionModel().selectedItemProperty().addListener(
                (observable, staraWartosc, nowaWartosc) -> {
                    if (nowaWartosc != null) {
                        etykietaInfo.setText("Chosen task: " + nowaWartosc.name);
                        label_info_name.setText(nowaWartosc.name);
                        label_info_description.setText(nowaWartosc.description);
                        label_info_priority.setText("Priority: " + nowaWartosc.priority.toString());
                        chosenTask = nowaWartosc;
                    }
                }
        );

        usun_zadanie.setOnAction(e -> {
            taskService.removeTask(taskList.getSelectionModel().getSelectedItem());
            etykietaInfo.setText("");
        });

        info_zadanie.setOnAction(e -> {
            stage.setScene(ekranInfo);
        });

        dodaj_zadanie.setOnAction(e -> stage.setScene(ekranDodawania));

        VBox layout1 = new VBox(20);
        HBox layoutbutton = new HBox(20);
        layoutbutton.setAlignment(Pos.CENTER);
        layoutbutton.getChildren().addAll(usun_zadanie,info_zadanie, dodaj_zadanie);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(etykietaInfo,taskList,layoutbutton);

        ekranGlowny = new Scene(layout1, 600, 450);



        // EKRAN INFO

        Button buttonBack2 = new Button("<< Back");
        buttonBack2.getStyleClass().add("button-blue");


        Button setDone = new Button("Set done");
        setDone.getStyleClass().add("button-green");

        Button setNotDone = new Button("Set not done");
        setNotDone.getStyleClass().add("button-red");



        buttonBack2.setOnAction(e -> stage.setScene(ekranGlowny));
        setDone.setOnAction(e -> chosenTask.setDone(true));
        setNotDone.setOnAction(e -> chosenTask.setDone(false));

        StackPane layout2 = new StackPane();
        HBox layoutbutton2 = new HBox(20);
        layoutbutton2.setAlignment(Pos.CENTER);
        layoutbutton2.getChildren().addAll(setDone,setNotDone);
        VBox layout4 = new VBox(20);
        layout4.setAlignment(Pos.CENTER);
        layout4.getChildren().addAll(label_info_description,label_info_priority,layoutbutton2);
        layout2.getChildren().addAll(label_info_name,layout4,buttonBack2);
        StackPane.setAlignment(label_info_name, Pos.TOP_CENTER);
        StackPane.setAlignment(buttonBack2, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(label_info_description, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(label_info_priority, Pos.CENTER);

        ekranInfo = new Scene(layout2, 600, 450);




        // EKRAN DODAWANIA
        Button buttonBack = new Button("<< Back");
        buttonBack.getStyleClass().add("button-success");

        Label label2 = new Label("Add new task ");
        label2.getStyleClass().add("duzy-tekst");

        buttonBack.getStyleClass().add("button-blue");

        Button buttonAdd = new Button("Add task");
        buttonAdd.getStyleClass().add("button-green");

        Button buttonGetInfo= new Button("Add task");
        buttonGetInfo.getStyleClass().add("button-blue");

        TextField poleName = new TextField();
        poleName.setPromptText("Enter the task name");

        TextField poleDescription = new TextField();
        poleDescription.setPromptText("Enter the task description");

        ChoiceBox<Priority> priorityChoiceBox = new ChoiceBox<>();
        priorityChoiceBox.getItems().addAll(Priority.values());
        priorityChoiceBox.setValue(Priority.MEDIUM);


        buttonBack.setOnAction(e -> stage.setScene(ekranGlowny));
        buttonAdd.setOnAction(e -> {
            if(poleName.getText().isEmpty() != true && poleDescription.getText().isEmpty() != true) {
                taskService.addTask(new Task(poleName.getText(), poleDescription.getText(), priorityChoiceBox.getValue()));
                poleName.clear();
                poleDescription.clear();
                stage.setScene(ekranGlowny);
            }
            else {
                pokazBlad("Input Error","Name or Description is incorrect.");
            }

        });

        StackPane layout_info = new StackPane();
        VBox layout3 = new VBox(20);
        layout3.setAlignment(Pos.CENTER_LEFT);
        layout3.getChildren().addAll(new Label("Enter name:"),poleName, new Label("Enter description:"), poleDescription,new Label("Enter priority:"),priorityChoiceBox);

        layout_info.getChildren().addAll(label2,layout3,buttonBack, buttonAdd);
        StackPane.setAlignment(label2, Pos.TOP_CENTER);
        StackPane.setAlignment(buttonBack, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(buttonAdd, Pos.BOTTOM_RIGHT);

        ekranDodawania = new Scene(layout_info, 600, 450);



        ekranDodawania.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        ekranGlowny.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        ekranInfo.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("Task Manager");
        stage.setScene(ekranGlowny);
        stage.show();
    }

    private void pokazBlad(String tytul, String tresc) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Typ ERROR dodaje czerwoną ikonę X
        alert.setTitle("Błąd");
        alert.setHeaderText(tytul);
        alert.setContentText(tresc);
        alert.showAndWait(); // To sprawia, że okno "wyskakuje" i blokuje resztę aplikacji do czasu kliknięcia OK
    }

    public static void main(String[] args) {
        launch();
    }
}