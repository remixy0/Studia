import java.util.Random;
import de.vandermeer.asciitable.AsciiTable;


public class Grades {
    static int [][] grades = new int[5][4];

    public static void randomGrades(){
        Random rand = new Random();
        for(int i = 0; i < grades.length; i++){
            for(int j = 0; j < grades[i].length; j++){
                grades[i][j] = rand.nextInt(2,6);
            }
        }
    }

    public static void averageForStudent(){
        int avg = 0;
        for(int i = 0; i < grades.length; i++) {
            int sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
            }
            avg = sum/grades.length;
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow("Wpisana wartość","Fahrenheita", "Celcjusza");
            at.addRule();
            at.addRow( temperatura_str + jednostka,fahrenheita_str, celcjusza_str);
            at.addRule();
        }
    }
}
