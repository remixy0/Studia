package Zad5;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        List<Passanger> passangers = new ArrayList<>();
        passangers.add(new VipPassanger("marek", true));
        passangers.add(new RegularPassanger("mateusz", true));
        passangers.add(new VipPassanger("krystian", false));
        passangers.add(new VipPassanger("filip", true));
        passangers.add(new RegularPassanger("ania", false));
        passangers.add(new RegularPassanger("pawel", false));

        System.out.println(passangers);
        passangers.stream().filter(x-> x instanceof VipPassanger).forEach(x -> x.checkedIn = true);
        List<Passanger> lista = passangers.stream().filter(x-> x.checkedIn == false).toList();
        lista.stream().forEach(x -> passangers.remove(x));
        System.out.println(passangers);

        System.out.println("number of vips: " + passangers.stream().filter(x-> x instanceof VipPassanger).count());
        System.out.println("number of regular passangers: " + passangers.stream().filter(x-> x instanceof RegularPassanger).count());


        Deque<Passanger> queue = new ArrayDeque<>();

        passangers.stream().filter(x-> x instanceof VipPassanger ).forEach(x -> queue.addFirst(x));
        passangers.stream().filter(x-> x instanceof RegularPassanger ).forEach(x -> queue.addLast(x));

        System.out.println("Queue: ");
        System.out.println(queue);



    }
}
