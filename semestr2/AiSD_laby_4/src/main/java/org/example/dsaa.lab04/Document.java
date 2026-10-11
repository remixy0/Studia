package org.example.dsaa.lab04;

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

	public static boolean isCorrectId(String id) {
        if (id.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
            return true;
        }
		return false;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static Link createLink(String link) {
        if (link.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
            return new Link(link);
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
}

