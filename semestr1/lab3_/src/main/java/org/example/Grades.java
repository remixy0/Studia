package org.example;
import java.util.Random;
import de.vandermeer.asciitable.AsciiTable;


public class Grades {
    int [][] grades = new int[5][4];
    private int bestStudentNumber;

    public void randomGrades(){
        Random rand = new Random();
        for(int i = 0; i < grades.length; i++){
            for(int j = 0; j < grades[i].length; j++){
                grades[i][j] = rand.nextInt(2,6);
            }
        }
    }

    public void averageForStudent(){
        int [] subject_sum = new int[4];
        double avg = 0;

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Student","Subject 1", "Subject 2","Subject 3","Subject 4","Average");
        for(int i = 0; i < grades.length; i++) {
            double sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
                subject_sum[j] += grades[i][j];
            }
            avg = sum/grades.length;
            at.addRule();
            at.addRow(i+1,grades[i][0], grades[i][1], grades[i][2], grades[i][3], avg);

        }
        at.addRule();
        at.addRow("Subject AVG",subject_sum[0]/5, subject_sum[1]/5, subject_sum[2]/5, subject_sum[3]/5, " ");
        at.addRule();
        String tabela = at.render();
        System.out.println(tabela);
    }

    public void bestStudent(){
        double avg = 0;
        double bestStudentAvg= 0;
        int bestStudentNumber = 0;
        for(int i = 0; i < grades.length; i++) {
            double sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
            }
            avg = sum/grades.length;
            if(avg > bestStudentAvg){
            bestStudentNumber = i + 1;
                bestStudentAvg = avg;
            }
        }
        System.out.println("best student number: " + bestStudentNumber + " with average grade: " + bestStudentAvg );

    }


}

