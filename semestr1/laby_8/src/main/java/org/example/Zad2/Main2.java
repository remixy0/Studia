package org.example.Zad2;

import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException {
        UI ui = new UI(new Service(new Repository()));
        ui.checkErrors();
    }
}
