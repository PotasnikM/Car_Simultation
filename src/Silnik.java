public class Silnik extends Komponent{
    private float max_obroty;
    private float obroty;
    private Boolean StanWłączenia = false;

    public Silnik() {
        super("silnik", 100, 1000);
        this.max_obroty = 5000;
        this.obroty = 0;
    }

    public Silnik( int max_obroty, String nazwa, float waga, float cena) {
        super(nazwa, waga, cena);
        this.max_obroty = max_obroty;
        this.obroty = 0;
    }

    public Silnik(String nazwa, float waga, float cena, float max_obroty, float obroty) {
        super(nazwa, waga, cena);
        this.max_obroty = max_obroty;
        this.obroty = obroty;
    }

    public float getObroty() {
        return obroty;
    }

    public void uruchom(){
        this.StanWłączenia = true;
    }
    public void zatrzymaj(){
        this.obroty = 0;
        this.StanWłączenia = false;

    }
    public void zwiększObroty(){
        if(this.StanWłączenia){
            if(this.obroty + 100 <= this.max_obroty){
                this.obroty += 100;
            }
        }
    }
    public void zmniejszObroty(){
        if(this.StanWłączenia){
            if(this.obroty - 100 >= 0){
                this.obroty -= 100;
            }
        }
    }
}
