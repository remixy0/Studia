package org.example.dsaa.lab04;

import java.util.Comparator;

public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	public Link(String ref) {
		this.ref=ref;
		weight=1;
	}

    public Link(String ref, int weight) {
		this.ref=ref;
        if(weight>0){
            this.weight=weight;
        }else{this.weight = 1;}

	}

    @Override
	public boolean equals(Object obj) {
		return this.ref.equals(((Link)obj).ref);
	}

    @Override
	public String toString() {
		return ref+"("+weight+")";
	}

    @Override
	public int compareTo(Link another) {
        return this.ref.compareTo(another.ref);
	}
}

