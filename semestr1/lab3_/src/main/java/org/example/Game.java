package org.example;
import java.util.Scanner;

import de.vandermeer.asciitable.AT_Renderer;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;

public class Game {
    String [][] board = new String[3][3];

    public Game () {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = " ";
            }
        }
    }

    void printBoard() {
        AsciiTable at = new AsciiTable();
        AT_Renderer renderer = at.getRenderer();
        renderer.setCWC(new CWC_FixedWidth().add(3).add(3).add(3));
        at.addRule();
        at.addRow(board[0][0],board[0][1],board[0][2]);
        at.addRule();
        at.addRow(board[1][0],board[1][1],board[1][2]);
        at.addRule();
        at.addRow(board[2][0],board[2][1],board[2][2]);
        at.addRule();
        String tabela = at.render();
        System.out.println(tabela);

    }

    boolean moveX(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Move X:");
        System.out.println("row column");
        String x = sc.nextLine();
        String[] parts = x.split("\\s+");

        if(parts.length!=2){return false;}
        if(Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[1]) > 3){return false;}

        if(board[Integer.parseInt(parts[0])-1][Integer.parseInt(parts[1])-1].equals(" ")){
            board[Integer.parseInt(parts[0])-1][Integer.parseInt(parts[1])-1] = " X";
            return true;
        }else{
            System.out.println("Position: " + parts[0] + " "+ parts[1]+" is not empty");
            return false;
        }
    }

    boolean moveO(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Move O:");
        System.out.println("row column");
        String o = sc.nextLine();
        String[] parts = o.split("\\s+");

        if(parts.length!=2){return false;}
        if(Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[1]) > 3){return false;}

        if(board[Integer.parseInt(parts[0])-1][Integer.parseInt(parts[1])-1].equals(" ")){
            board[Integer.parseInt(parts[0])-1][Integer.parseInt(parts[1])-1] = " O";
            return true;
        }else{
            System.out.println("Position: " + parts[0] + " "+ parts[1]+" is not empty");
            return false;
        }
    }

    public void startGame(){
        int i = 0;
        printBoard();
        while(!isGameOver()){
            if(i%2==0){
                while(!moveX()){}
            }else{
               while(!moveO()){}
            }
            printBoard();
            i++;
        }
    }

    boolean isGameOver(){
        if(checkRow() != ""){
            System.out.println(checkRow() + " Wins");
            return true;}
        else if(checkColumn() != ""){
            System.out.println(checkColumn() + " Wins");
            return true;
        }
        else if(checkDiagonal() != ""){
            System.out.println(checkDiagonal() +" Wins");
            return true;
        }
        else if(checkDraw() != ""){
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    String checkRow(){
        for(int i=0;i<3;i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2]) && board[i][0] != " ") {
                return board[i][0];
            }
        }
        return "";
    }

    String checkColumn(){
        for(int i=0;i<3;i++) {
            if ((board[0][i] == board[1][i] && board[1][i] == board[2][i]) && board[0][i] != " ") {
                return board[0][i];
            }
        }
        return "";
    }

    String checkDiagonal(){
        if((board[0][0] == board[1][1] && board[1][1] == board[2][2]) && board[0][0] != " "){
            return board[0][0];
        }
        else if((board[0][2] == board[1][1] && board[1][1] == board[2][0])  && board[0][2] != " "){
            return board[0][2];
        }
        return "";
    }

    String checkDraw(){
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++){
                if(board[i][j] == " "){
                    return "";
                }
            }
        }
        return "gramy";
    }


}
