package org.example;
import java.util.Random;



public class TemperatureSensor {
    double [][] sensors = new double [5][8];


    public void fillSensors(double [][] sensors){
        Random random = new Random();
        for(int i=0;i<sensors.length;i++){
            for(int j=0;j<sensors[i].length;j++){
                sensors[i][j] = random.nextDouble(20,40);
            }
        }
    }

    void averageTemperature(double [][] sensors){
        for(int i=0;i<sensors.length;i++){
            double sum = 0;
            for(int j=0;j<sensors[i].length;j++){
                sum+=sensors[i][j];
            }
            System.out.println("Sensor "+i+" average: "+sum/8);
        }
    }

    void highestAvgTemperature(double [][] sensors){
        double max = 0;
        for(int i=0;i<sensors.length;i++){
            double sum = 0;
            for(int j=0;j<sensors[i].length;j++){
                sum+=sensors[i][j];
            }
            if(sum/8>max){
                max = sum/8;
            }
        }
        System.out.println("Highest average temperature: "+max);
    }

    void findTimeOfMaxTemperature(double [][] sensors){
        double max = 0;
        int godzina = 0;
        for(int i=0;i<sensors.length;i++){
            for(int j=0;j<sensors[i].length;j++){
                if(sensors[i][j] >max){
                    max=sensors[i][j];
                    godzina=j;
                }
            }
        }
        System.out.println("Highest temperature was measured on : "+godzina);
    }






}
