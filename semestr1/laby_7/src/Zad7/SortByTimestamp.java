package Zad7;

import java.sql.Timestamp;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.Locale;

public class SortByTimestamp implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        LocalDateTime timestamp1 = ((Transaction) o1).timestamp;
        LocalDateTime timestamp2 = ((Transaction) o2).timestamp;
        return timestamp1.compareTo(timestamp2);

    }
}
