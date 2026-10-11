package org.example.App.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Effort {
    static int idCounter = 0;
    int id;
    String athleteName;
    String athleteSurname;
    SensorType sensorType;
    Double dataRate;
    List<Double> data;

    public Effort() {
        this.id = ++idCounter;

    }

    public Effort(String athleteName, String athleteSurname, SensorType sensorType ,List<Double> data) {
        this.id = ++idCounter;
        this.athleteName = athleteName;
        this.athleteSurname = athleteSurname;
        this.sensorType = sensorType;
        this.data = data;
    }

}



