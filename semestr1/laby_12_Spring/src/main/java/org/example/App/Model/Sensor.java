package org.example.App.Model;

public interface Sensor {
    static int idCounter = 0;
    double averageSpeed();
    double averageAcceleration();
    double timeOfMovement();
    int getId();
    void setData(Effort effort);

}
