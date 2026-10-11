package Zad7;

import java.util.Comparator;
import java.util.LinkedList;

public class SortByLenght implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        LinkedList list1 = (LinkedList) o1;
        LinkedList list2 = (LinkedList) o2;
        if (list1.size() < list2.size()) {
            return -1;
        }
        if (list1.size() > list2.size()) {
            return 1;
        }
        return 0;
    }
}
