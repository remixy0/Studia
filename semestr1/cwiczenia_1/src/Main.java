public class Main {
    public static void main(String[] args) {
    Data data = new Data(12,1,2024);
    System.out.println(data.toString());
    data.ustawDate(13,11,1967);
    System.out.println(data.toString());
    System.out.println(data.ileDni(2,2024));
    }
}
