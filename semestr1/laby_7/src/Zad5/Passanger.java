package Zad5;

public abstract class Passanger {
    String name;
    boolean checkedIn;
    public Passanger(String name, boolean checkedIn) {
        this.name = name;
        this.checkedIn = checkedIn;

    }

    @Override
    public String toString() {
        return name + " " + checkedIn;
    }

}
