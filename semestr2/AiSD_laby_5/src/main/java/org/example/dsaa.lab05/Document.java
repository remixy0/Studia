package org.example.dsaa.lab05;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
    public void load(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.equals("eod")){
                break;
            }else{
                lineAnalyser(line);
            }
        }
    }
	

    public void lineAnalyser(String line){
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
            String element = lineScanner.next();
            correctLink(element);
        }
    }

    public static boolean isCorrectId(String id) {
        if (id.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
            return true;
        }
        return false;
    }

    public boolean correctLink(String link) {
        if(!link.toLowerCase().startsWith("link=")){
            return false;
        }

        Integer wagaId = -1;
        String text = link.toLowerCase().substring(5);

        try{
            String waga = text.substring(text.indexOf("(")+1,text.indexOf(")"));
            wagaId = Integer.parseInt(waga);
            text = text.substring(0,text.indexOf("("));
            if (text.matches("^[a-zA-Z][a-zA-Z0-9_]*$") && wagaId>0) {
                this.link.add(new Link(text,wagaId));
                return true;
            }

        }catch(Exception e){
            if (text.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
                this.link.add(new Link(text));
                return true;
            }
        }



        return false;
    }

    public static Link createLink(String link) {
        Integer wagaId = -1;
        String text = null;
        if(link.toLowerCase().startsWith("link=")){
            text = link.toLowerCase().substring(5);
        }else{
            text = link.toLowerCase();
        }

        try{
            String waga = text.substring(text.indexOf("(")+1,text.indexOf(")"));
            wagaId = Integer.parseInt(waga);
            text = text.substring(0,text.indexOf("("));
            if (text.matches("^[a-zA-Z][a-zA-Z0-9_]*$") && wagaId>0) {
                return new Link(text,wagaId);
            }

        }catch(Exception e){
            if (text.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
                return new Link(text);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        Iterator<Link> it = link.iterator();
        String wyraz = "Document: " + name+ "\n";
        int licznik = 0;
        while(it.hasNext()){
            licznik++;
            Link link = it.next();
            wyraz += link.ref +"(" + link.weight +")";
            if(licznik==10){
                wyraz += "\n";
            }else{wyraz += " ";}
        }
        return wyraz.strip();
    }

    public String toStringReverse() {
        String retStr="Document: "+name + "\n";
        ListIterator<Link> iter=link.listIterator();
        Link lastLink = null;
        while(iter.hasNext())
            lastLink = iter.next();
        int licznik = 1;
        if(lastLink!=null){
            retStr += lastLink.ref +"(" + lastLink.weight +") ";
        }

        while(iter.hasPrevious()){
            licznik++;
            Link link = iter.previous();
            retStr += link.ref +"(" + link.weight +")";
            if(licznik==10){
                retStr += "\n";
            }else{retStr += " ";}
        }
        return retStr.strip();
    }

	public int[] getWeights() {
        int[] arr = new int[link.size()];
        Iterator<Link> it = link.iterator();
        int licznik = 0;
        while(it.hasNext()){
            Link link = it.next();
            arr[licznik] = link.weight;
            licznik++;
        }
		return arr;
	}

	public static void showArray(int[] arr) {
		for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
            if(i != arr.length-1){
                System.out.print(" ");
            }else{
                System.out.println();
            }
        }
	}

    void bubbleSort(int[] arr) {
        showArray(arr);
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = arr.length - 1; j > i; j--) {

                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
            showArray(arr);
        }
    }

    public void insertSort(int[] arr) {
        showArray(arr);

        for (int i = arr.length - 2; i >= 0; i--) {
            int key = arr[i];
            int j = i + 1;

            while (j < arr.length && arr[j] < key) {
                arr[j - 1] = arr[j];
                j++;
            }

            arr[j - 1] = key;

            showArray(arr);
        }
    }




    public void selectSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = i;

                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[maxIndex]) {
                        maxIndex = j;
                    }
                }

                if (maxIndex != i) {
                    int temp = arr[maxIndex];
                    arr[maxIndex] = arr[i];
                    arr[i] = temp;
                }
                showArray(arr);
            }
        }

}
