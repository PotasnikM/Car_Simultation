import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SamochódGUI extends Thread{
    private JPanel Samochód;
    private JTextField Model;
    private JTextField Nrresjest;
    private JTextField sam_waga;
    private JTextField Prędkość;
    private JComboBox comboBox1;
    private JButton dodajNowyButton;
    private JButton usuńButton;
    private JButton włączButton;
    private JButton wyłączButton;
    private JButton biegButton;
    private JButton biegButton1;
    private JButton OdejmijGaz;
    private JButton DodajGaz;
    private JButton Zwolnij;
    private JButton Naciśnij;
    private JTextField sk_Nazwa;
    private JTextField sk_Cena;
    private JTextField sk_Waga;
    private JTextField sk_Bieg;
    private JTextField sl_Nazwa;
    private JTextField sl_Cena;
    private JTextField sl_Waga;
    private JTextField sl_Obroty;
    private JTextField sp_Nazwa;
    private JTextField sp_Cena;
    private JTextField sp_Waga;
    private JTextField sp_Stan;
    private JPanel Mapa;
    private JLabel Sam_icon;
    private int cel_x;
    private int cel_y;


    private Samochód samochód;



    public SamochódGUI(Samochód sam) {
        this.samochód = sam;
        wyłączButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.wylacz();
                refresh();
            }
        });
        włączButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.wlancz();
                refresh();
            }
        });
        biegButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.zmniejszBieg();
                refresh();
            }
        });
        biegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.zwiększBieg();
                refresh();
            }
        });
        Zwolnij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.sprzeglo.zwolnij();
                refresh();
            }
        });
        Naciśnij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.sprzeglo.wcisnij();
                refresh();
            }
        });
        usuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.removeItem(samochód);
                try {
                    samochód.interrupt();
                } catch (NullPointerException n){

                }
                refresh();
            }
        });
        OdejmijGaz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.silnik.zmniejszObroty();
                refresh();
            }
        });
        DodajGaz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.silnik.zwiększObroty();
                refresh();
            }
        });

        Mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(comboBox1.getItemCount() != 0) {
                    super.mousePressed(e);
                    cel_x = e.getX();
                    cel_y = e.getY();
                    samochód.jedźDo(new Pozycja(cel_x, cel_y));
                    refresh();
                }
            }
        });
        dodajNowyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new NowySamochodGUI(comboBox1);
                f.pack();
                f.setResizable(false);
                f.setVisible(true);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód = (Samochód) comboBox1.getSelectedItem();
            }
        });
        start();
    }
    private void refresh(){
        if(comboBox1.getItemCount() != 0){
            Model.setText(samochód.getModel());
            Nrresjest.setText((samochód.getNrRejest()));
            sam_waga.setText(Float.toString(samochód.getWaga()));
            Prędkość.setText(Double.toString(samochód.getAktPredkosc()));
            sk_Nazwa.setText(samochód.skrzynia.getNazwa());
            sk_Cena.setText(Float.toString(samochód.skrzynia.getCena()));
            sk_Waga.setText(Float.toString(samochód.skrzynia.getWaga()));
            sk_Bieg.setText(Integer.toString(samochód.skrzynia.getAktualnyBieg()));
            sl_Nazwa.setText(samochód.silnik.getNazwa());
            sl_Cena.setText(Float.toString(samochód.silnik.getCena()));
            sl_Waga.setText(Float.toString(samochód.silnik.getWaga()));
            sl_Obroty.setText(Float.toString(samochód.silnik.getObroty()));
            sp_Nazwa.setText(samochód.skrzynia.sprzeglo.getNazwa());
            sp_Cena.setText(Float.toString(samochód.skrzynia.sprzeglo.getCena()));
            sp_Waga.setText(Float.toString(samochód.skrzynia.sprzeglo.getWaga()));
            sp_Stan.setText(Boolean.toString(samochód.skrzynia.sprzeglo.isStanSprzegla()));
            Sam_icon.show();
            Sam_icon.setLocation((int) samochód.getAktPozycja().getX(),(int) samochód.getAktPozycja().getY());

        }
        else {
            Model.setText("");
            Nrresjest.setText("");
            sam_waga.setText("");
            Prędkość.setText("");
            sk_Nazwa.setText("");
            sk_Cena.setText("");
            sk_Waga.setText("");
            sk_Bieg.setText("");
            sl_Nazwa.setText("");
            sl_Cena.setText("");
            sl_Waga.setText("");
            sl_Obroty.setText("");
            sp_Nazwa.setText("");
            sp_Cena.setText("");
            sp_Waga.setText("");
            sp_Stan.setText("");
            Sam_icon.hide();
        }
    }
    public void run(){
        while (true){
            refresh();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void main(String[] args) {
        Pozycja aktPozycja_a = new Pozycja(0,0);
        Silnik silnik = new Silnik(6500, "2981cc", 200, 20000);
        SkrzyniaBiegów skrzynia = new SkrzyniaBiegów(6, "6_biegowa", 350, 3000);
        Samochód car_a = new Samochód(true,"KNT 1122","911", "Porsche",330,aktPozycja_a,skrzynia,silnik);
        JFrame frame = new JFrame("SamochódGUI");
        frame.setContentPane(new SamochódGUI(car_a).Samochód);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
