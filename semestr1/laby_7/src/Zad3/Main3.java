package Zad3;

import java.util.LinkedList;
import java.util.Queue;

public class Main3 {
    public static void main(String[] args) {
        Queue<Customer> queue = new LinkedList<>();
        queue.offer(new Customer("Jack", 5));
        queue.offer(new Customer("Peter", 8));
        queue.offer(new Customer("Mark", 4));
        queue.offer(new Customer("Thomas", 12));
        int queueSize = queue.size();
        double serviceTime = 0;
        double waitingTime = 0;

        while(queue.size()>0){
            Customer c = queue.poll();
            System.out.println("Serving for  " + c.name + " service time: " + c.serviceTime);
            waitingTime += c.waitingTime;
            serviceTime += c.serviceTime;
            for(Customer customer: queue) customer.waitingTime += c.serviceTime;
        }
        System.out.println("Avg waiting time: " + waitingTime/queueSize);
        System.out.println("Avg service time: " + serviceTime/queueSize);


    }
}
