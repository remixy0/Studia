package org.example.Zad4;


public class User {
    String username;
    String email;
    int age;

    public User(String username, String email, int age) throws UserClassException {
        if (username == null || username.isEmpty()) { throw new UserClassException("Username is empty");}
        if (!email.contains("@")) { throw new UserClassException("Email doesnt contain @");}
        if (age < 0) { throw new UserClassException("Age is < 0");}
        this.username = username;
        this.email = email;
        this.age = age;
    }



}
