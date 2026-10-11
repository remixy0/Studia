package org.example;

import java.util.Random;

public class Scores {
    int[] scores;

    public void scoresCreate(int size, int maxValue) {
        int[] numbers = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = rand.nextInt(maxValue);
        }
        this.scores = numbers;
    }


    public int average(){
        int sum = 0;
        for(int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        return sum/scores.length;
    }

    public int max(){
        int max = 0;
        for(int i = 0; i < scores.length; i++) {
            if(scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public int min(){
        int min = 101;
        for(int i = 0; i< scores.length; i++){
            if(scores[i] < min){
                min = scores[i];
            }
        }
        return min;
    }

    public int numberOfPassedExams(){
        int count = 0;
        for(int i = 0; i<scores.length; i++){
            if(scores[i] > 50){
                count++;
            }
        }
        return count;
    }
}
