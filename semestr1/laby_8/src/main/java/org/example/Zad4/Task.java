package org.example.Zad4;

public class Task {
    String title;
    String description;
    Boolean isDone;
    public Task(String title, String description,  Boolean isDone) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public String getName() {
        return title;
    }

    public void setDone(){
        isDone = true;
    }

    @Override
    public String toString() {
        return title;
    }
}
