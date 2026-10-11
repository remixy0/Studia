package org.example.App.Repository;

import org.example.App.Model.Effort;
import org.example.App.Model.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<Sensor> sensors = new ArrayList<>();

    public String getEfforts() {
        String effortsString = "";
        for (Sensor effort : sensors) {
            effortsString += "   " + effort.toString();
        }
        return effortsString;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }


    public String getAnalysis() {
        String effortsString = "";
        for (Sensor effort : sensors) {
            effortsString += effort.toString() + "    was moving for: " + effort.timeOfMovement()+ "s   with average speed of: " + effort.averageSpeed() + "m/s    and acceleration of: "+ effort.averageAcceleration() + "m/s^2       ";

        }
        return effortsString;
    }


    public void addSensor(Sensor effort) {
        sensors.add(effort);
    }


    public boolean updateSensor(int id, Effort newEffort) {
        for (int i = 0; i < sensors.size(); i++) {
            Sensor sensor = sensors.get(i);

            if (sensor.getId() == id) {
                sensor.setData(newEffort);
                return true;
            }

        }
        return false;
    }

    public boolean deleteSensor(int id) {
        for (int i = 0; i < sensors.size(); i++) {
            Sensor sensor = sensors.get(i);
            if (sensor.getId() == id) {
                sensors.remove(i);
                return true;
            }
        }
        return false;
    }

}
