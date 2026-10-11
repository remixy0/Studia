package org.example.Zad1;

public class Main1 {
    public static void main(String[] args) {
        UI ui = new UI(new Service(new Repository()));
        try {
            ui.readFileFromInput();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
