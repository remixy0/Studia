package Zad4;

public class Zad4 {
    public static void sumyKwadratow(double n){
        for(int i=1; i <= n ;i++){
            int sum = 0;
            int liczba = i;
            String string = String.valueOf(i);

            while(liczba > 0){
                sum += Math.pow(liczba%10 , string.length());
                liczba /= 10;
            }
            if(sum == i){
                System.out.println(i);
            }
        }
    }
}
