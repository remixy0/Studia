module com.przyklad {
    // 1. Mówimy, że potrzebujemy kontrolek JavaFX (przyciski, okna)
    requires javafx.controls;
    requires javafx.base;

    // 2. Jeśli używasz plików .fxml, odkomentuj linię poniżej:
    // requires javafx.fxml;

    // 3. Pozwalamy bibliotece JavaFX "widzieć" nasz kod, żeby mogła go uruchomić
    exports org.example;

    // 4. Jeśli używasz FXML, musisz też "otworzyć" pakiet dla FXML loadera:
    // opens com.przyklad to javafx.fxml;
}