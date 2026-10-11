package org.example.App.Model;

import java.util.ArrayList;
import java.util.List;

public class LiDAR implements Sensor{
    int id;
    Effort effort;
    private List<Double> distances;
    private double period;

    public LiDAR(Effort effort) {
        this.id = effort.id;
        this.effort = effort;
        distances = effort.getData();
        period = 1/effort.getDataRate();
    }


    @Override
    public double averageSpeed() {
        double sum = calculateSpeed().stream().mapToDouble(Double::doubleValue).sum();
        return sum/calculateSpeed().size();
    }

    @Override
    public double averageAcceleration() {
        double sum = calculateAcceleration().stream().mapToDouble(Double::doubleValue).sum();
        return sum/calculateAcceleration().size();
    }

    @Override
    public double timeOfMovement() {
        return (finishIndex() - startIndex())*period;
    }

    @Override
    public String toString() {
        return effort.getAthleteName() + " " + effort.getAthleteSurname() + "  sensor type: "+  effort.getSensorType() + "   id:" + getId();
    }

    private int startIndex(){
        for(int i=0; i<calculateSpeed().size(); i++){
            if(calculateSpeed().get(i) > 1) return i;
        }
        return 0;
    }

    private int finishIndex(){
        for(int i=startIndex(); i<calculateSpeed().size(); i++){
            if(calculateSpeed().get(i) < 0.1) return i;
        }
        return calculateSpeed().size();
    }


    private List<Double> calculateSpeed() {
        List<Double> speed = new ArrayList<>();
        double previousDistance = distances.get(0);
        for(int i = 1; i < distances.size(); i++){
            speed.add((distances.get(i) - previousDistance)/period);
            previousDistance = distances.get(i);
        }
        return speed;
    }

    private List<Double> calculateAcceleration() {
        List<Double> acceleration = new ArrayList<>();
        double previousSpeed = calculateSpeed().get(0);
        for(int i = 1; i < calculateSpeed().size(); i++){
            acceleration.add((calculateSpeed().get(i) - previousSpeed)/period);
            previousSpeed = calculateSpeed().get(i);
        }
        return acceleration;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setData(Effort effort) {
        this.effort = effort;
        distances = effort.getData();
        period = 1/ effort.getDataRate();
    }

}
