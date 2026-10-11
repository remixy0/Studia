package Zad4;

import java.util.*;

public class Main4 {
    static List<Ticket> tickets = new ArrayList<>();
    static Queue<Ticket> highPriorityQueue = new LinkedList<>();
    static Queue<Ticket> normalQueue = new LinkedList<>();

    public static void main(String[] args) {


        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            tickets.add(new Ticket(i, "Error", "User " + i, rand.nextInt(11)));
        }

        System.out.println(tickets);
        System.out.println(findTicketOfUser("User 2"));
        handleTickets();



    }


    static List<Ticket> findTicketOfUser(String user) {
        List<Ticket> ticketsOfUsers = new ArrayList<>();
        tickets.stream().filter(ticket -> ticket.user.equals(user)).forEach(ticket -> ticketsOfUsers.add(ticket));
        return ticketsOfUsers;
    }


    static void handleTickets() {
        tickets.stream().filter(x -> x.getPriority() >= 9).forEach(x -> {
            highPriorityQueue.add(x);
            System.out.println(x + "  Goes to the high priority queue");
        });

        tickets.stream().filter(x -> x.getPriority() < 9).forEach(x -> {
            normalQueue.add(x);
            System.out.println(x + "  Goes to the normal queue");
        });

        tickets.clear();
    }
}
