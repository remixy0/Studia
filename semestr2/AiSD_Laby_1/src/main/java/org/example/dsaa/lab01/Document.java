package org.example.dsaa.lab01;

import java.io.File;
import java.util.Scanner;

public class Document {
	public static void loadDocument(String name, Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.equals("eod")){
                break;
            }else{
            lineAnalyser(line);
            }
        }
	}

	public static boolean correctLink(String link) {
        if(!link.toLowerCase().startsWith("link=")){
            return false;
        }
        String text = link.toLowerCase().substring(5);
        if (text.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
            System.out.println(text);
            return true;
        }
        return false;
	}

    public static void lineAnalyser(String line){
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
            String element = lineScanner.next();
            correctLink(element);
        }
    }

}
