public class Sprzeglo extends Komponent{
    private boolean stanSprzegla;

    public Sprzeglo(){
        super("sprzęgło", 100, 100);
        this.stanSprzegla = true;
    }

    public void wcisnij(){
        this.stanSprzegla = true;
    }
    public void zwolnij(){
        this.stanSprzegla = false;
    }

    public boolean isStanSprzegla() {
        return stanSprzegla;
    }
}
