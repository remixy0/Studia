package Zad7;
import java.sql.Timestamp;
import java.time.LocalDateTime;
public class Transaction {
    String user;
    double amount;
    String currency;
    LocalDateTime timestamp;
    public Transaction(String user, double amount, String currency) {
        this.user = user;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return user + " " + amount + currency;
    }
}
