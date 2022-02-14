public class Samochód extends Thread{

    private boolean StanWłączenia;
    private String nrRejest;
    private String model;
    private String marka;
    private double prędkość_max;
    private Pozycja aktualnaPozycja;
    public SkrzyniaBiegów skrzynia;
    public Silnik silnik;
    final static int dt = 1;
    private Pozycja cel;

    public Samochód(boolean stanWłączenia, String nrRejest, String model, String marka, double prędkość_max, Pozycja aktualnaPozycja, SkrzyniaBiegów skrzynia, Silnik silnik) {
        this.StanWłączenia = stanWłączenia;
        this.nrRejest = nrRejest;
        this.model = model;
        this.prędkość_max = prędkość_max;
        this.aktualnaPozycja = aktualnaPozycja;
        this.skrzynia = skrzynia;
        this.silnik = silnik;
        this.cel = aktualnaPozycja;
        this.marka = marka;
        start();
    }
    public String getAlias(){
        return getNrRejest() + "," + getMarka() + "," + getModel();
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public boolean isStanWłączenia() {
        return StanWłączenia;
    }

    public String getNrRejest() {
        return nrRejest;
    }

    public String getModel() {
        return model;
    }

    public double getPrędkość_max() {
        return prędkość_max;
    }

    public void wlancz(){
        this.silnik.uruchom();
        this.StanWłączenia = true;
    }

    public void wylacz(){
        this.silnik.zatrzymaj();
        this.StanWłączenia = false;
    }

    public void jedźDo(Pozycja cel_){
        this.cel = cel_;
    }
    public void run(){
        while (true){
            aktualnaPozycja.przemiesc(this.getAktPredkosc() ,dt, this.cel);
            System.out.print("");

        }
    }

    public float getWaga(){
        float waga = skrzynia.sprzeglo.getWaga() + silnik.getWaga() + skrzynia.getWaga() + 1000;
        return waga;
    }

    public Pozycja getAktPozycja(){
        return this.aktualnaPozycja;
    }

    public double getAktPredkosc(){
        double prędkość = this.silnik.getObroty() * this.skrzynia.getAktualnePrzelozenie() * 0.019;
        if(aktualnaPozycja.getX() != cel.getX() || aktualnaPozycja.getY() != cel.getY()) {
            if (prędkość > prędkość_max) {
                prędkość = prędkość_max;
            }
        }
        else {
            prędkość = 0;
        }
        return prędkość;
    }
}
