package org.example.dsaa.lab02;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
	public String name;
	public OneWayLinkedList<Link> links;
	public Document(String name, Scanner scan) {
        links = new OneWayLinkedList<>();
        if(!links.isEmpty()){
            links.clear();
        }
        this.name=name;
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

    public boolean correctLink(String link) {
        if(!link.toLowerCase().startsWith("link=")){
            return false;
        }
        String text = link.toLowerCase().substring(5);
        if (text.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
            this.links.add(new Link(text));
            return true;
        }
        return false;
    }

    public void lineAnalyser(String line){
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
            String element = lineScanner.next();
            correctLink(element);
        }
    }

	@Override
	public String toString() {
        Iterator<Link> it = links.iterator();
        String wyraz = "Document: " + name+ "\n";
        while(it.hasNext()){
            Link link = it.next();
            wyraz += link.ref + "\n";
        }
		return wyraz.strip();
	}

}
