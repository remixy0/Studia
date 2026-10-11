package org.example.dsaa.lab03;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name;
		link=new TwoWayUnorderedListWithHeadAndTail<Link>();
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
            this.link.add(new Link(text));
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
        Iterator<Link> it = link.iterator();
        String wyraz = "Document: " + name+ "\n";
        while(it.hasNext()){
            Link link = it.next();
            wyraz += link.ref + "\n";
        }
        return wyraz.strip();
    }
	
	public String toStringReverse() {
		String retStr="Document: "+name;
		return retStr+link.toStringReverse();
	}

}
