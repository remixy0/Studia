package Zad3;

public class Main3 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Osoba osoba1 = new Osoba("Marek", "Kacperczyk");
        Osoba osoba2 = new Osoba("Marek", "Maciejczak");


        Konto konto1 = new Konto(osoba1, "1111345", 192.8);
        Konto konto2 = new Konto(osoba1, "222222", 2831.97);
        Konto konto3 = new Konto(osoba1, "223333", 22313.87);
        Konto konto4 = new Konto(osoba2, "999999", 100);

        bank.dodajKonto(konto1);
        bank.dodajKonto(konto2);
        bank.dodajKonto(konto3);
        bank.dodajKonto(konto4);


        bank.wplac(12.99,konto1);

        bank.kontaOsoby(osoba1);
    }
}
