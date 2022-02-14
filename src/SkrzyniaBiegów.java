public class SkrzyniaBiegów extends Komponent{
    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;
    public Sprzeglo sprzeglo;

    public void setIloscBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
    }

    public SkrzyniaBiegów() {
        super("skrzynia", 500, 5000);
        this.aktualnePrzelozenie = 1.2;
        this.aktualnyBieg = 1;
        this.iloscBiegow = 5;
        this.sprzeglo = new Sprzeglo();
    }
    public SkrzyniaBiegów(int iloscBiegow, String nazwa, float waga, float cena) {
        super(nazwa, waga, cena);
        this.aktualnePrzelozenie = 1.2;
        this.aktualnyBieg = 1;
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = new Sprzeglo();
    }

    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public double getAktualnePrzelozenie() {
        return aktualnePrzelozenie;
    }
    public void zwiększBieg(){
        if (sprzeglo.isStanSprzegla()){
            if (this.aktualnyBieg + 1 <= this.iloscBiegow) {
                this.aktualnyBieg += 1;
                this.aktualnePrzelozenie += 0.3;
            }
        }
    }
    public void zmniejszBieg(){
        if (sprzeglo.isStanSprzegla()) {
            if (this.aktualnyBieg - 1 > 0) {
                this.aktualnyBieg -= 1;
                this.aktualnePrzelozenie -= 0.3;
            }
        }
    }
}
