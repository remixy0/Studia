package Zad3;


public class Bank{
    Konto[] konta;

    public Bank(){
        konta = new Konto[1];
    }


    public int znajdzMiejsce(){
        for(int j = 0; j < konta.length; j++){
            if(konta[j] == null){
                return j;
            }
        }
        return -1;
    }

    public void dodajKonto(Konto konto){
        if(znajdzMiejsce() != -1){
            konta[znajdzMiejsce()]= konto;

        }else{
            Konto[] konta2 = new Konto[konta.length*2];
            System.arraycopy(konta, 0, konta2, 0, konta.length);
            konta2[konta.length/2] = konto;
            this.konta = konta2;
        }
    }

    public void wplac(double kwota, Konto konto){
        konto.wplata(kwota);
    }

    public void wyplata(double kwota, Konto konto){
        konto.wyplata(kwota);
    }

    public void kontaOsoby(Osoba osoba){
        for(int j = 0; j < konta.length; j++) {
            if (konta[j] != null) {
                if(konta[j].getOsoba().equals(osoba)) {
                    System.out.println(konta[j].getNumer() + "  " + konta[j].getKwota());
                }
            }
        }
    }

}



class Konto {
    Osoba osoba;
    String numer;
    Double kwota;

    public Konto(Osoba osoba, String numer, double kwota){
        this.osoba = osoba;
        this.numer = numer;
        this.kwota = kwota;
    }

    public void wplata(double kwota){
        this.kwota = this.kwota + kwota;
    }

    public void wyplata(double kwota){
        if(this.kwota >= kwota) {
            this.kwota = this.kwota - kwota;
        }else{
            System.out.println("Brak srodkow na koncie");
        }
    }



    public Osoba getOsoba(){return osoba;}
    public String getNumer(){return numer;}
    public double getKwota(){return kwota;}

    public void setOsoba(Osoba osoba){this.osoba = osoba;}
    public void setNumer(String numer){this.numer = numer;}
    public void setKwota(double kwota){this.kwota = kwota;}


    @Override
    public String toString(){
        return osoba + " " + numer + " " + kwota;

    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Konto){
            Konto k = (Konto)obj;
            if (this.toString().equals(k.toString())){return true;}
        }
        return false;
    }

}

class Osoba{
    String imie;
    String nazwisko;

    public Osoba(String imie, String nazwisko){
        this.imie = imie;
        this.nazwisko = nazwisko;
    }


    @Override
    public String toString(){
        return imie + " " + nazwisko;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Osoba){
            Osoba k = (Osoba) obj;
            if (this.toString().equals(k.toString())){return true;}
        }
        return false;
    }


}
