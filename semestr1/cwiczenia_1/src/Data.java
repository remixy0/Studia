public class Data {
    int dzien;
    int miesiac;
    int rok;


    public Data(){
        this.dzien = 27;
        this.miesiac = 10;
        this.rok = 2025;
    }
    public Data(int dzien, int miesiac, int rok){
        if(czyPoprawna(dzien,miesiac) == true){
            this.dzien = dzien;
            this.miesiac = miesiac;
            this.rok = rok;
        }
    }

    public int getDzien() {
        return dzien;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public int getRok() {
        return rok;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }
    public String toString(){
        return String.valueOf(dzien) + "." + String.valueOf(miesiac) + "." + String.valueOf(rok);
    }
    public void ustawDate(int dzien, int miesiac, int rok) {
        this.dzien = dzien;
        this.miesiac = miesiac;
        this.rok = rok;
    }

    public boolean czyPrzestepny(int rok){
        if (rok % 4 == 0 && (rok % 100 != 0 || rok % 400 == 0)){
            return true;
        }
        return false;
    }

    public boolean czyPoprawna(int dzien, int miesiac){
        if (dzien <= 31 && dzien > 0 && miesiac <= 12 &&  miesiac > 0){
            return true;
        }else {
            System.out.println("ERRRORORRRRRR");
            return false;

        }
    }

    public int ileDni(int miesiac, int rok) {
        if (miesiac == 1 || miesiac == 3 || miesiac == 5 || miesiac == 7 || miesiac == 8 || miesiac == 10 || miesiac == 12) {
            return 31;
        } else if(czyPrzestepny(rok) == true && miesiac == 2){
            return 29;
        }else if(czyPrzestepny(rok) == false && miesiac == 2){
            return 28;
        }
        else return 30;
    }
}
