package org.example;

import java.util.ArrayList;
import java.util.List;

public class AdminPanel {
    List<UserAccount> userAccounts = new ArrayList<UserAccount>();

    public void addUser(UserAccount userAccount) {
        userAccounts.add(userAccount);
    }

    public void blockUser(UserAccount userAccount) {
        userAccount.blockUser();
    }

    public void showUsers(){
        System.out.println("");
        for(UserAccount userAccount : userAccounts){
            System.out.println("User: " + userAccount.username + "     is active: " + userAccount.isActive);
        }
    }


}


class UserAccount{
    String username;
    String password;
    boolean isActive = true;

    public UserAccount(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void blockUser(){
        isActive = false;
    }


}
