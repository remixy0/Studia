package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.util.Random;

public class Matrix {
    static Random random = new Random();

    public static double [][] setMatrix(){
        double[][] matrix = new double[5][];
        for(int i = 0; i < matrix.length; i++){
            int len = random.nextInt(9) + 1;
            matrix[i] = new double[len];
            for(int j = 0; j < len; j++){
                matrix[i][j] = random.nextInt(99);
            }
        }
        return matrix;
    }

    public static void printMatrix(double [][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void fillMatrix(double [][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = random.nextInt(99);
            }
        }
    }

    public static void findMax(double [][] matrix){
        double max = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                }
            }
        }
        System.out.println("max = " + max);

    }
}
