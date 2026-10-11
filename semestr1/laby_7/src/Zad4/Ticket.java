package Zad4;

public class Ticket {
    int id;
    String title;
    String user;
    int priority;
    boolean resolved;
    Ticket(int id, String title, String user, int priority) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.priority = priority;
        this.resolved = false;
    }

    @Override
    public String toString() {
        return this.id + " "+  title + " " + user + " " + priority;
    }

    public int getPriority() {
        return priority;
    }




}
