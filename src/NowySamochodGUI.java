import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowySamochodGUI extends JFrame{
    private JPanel panel1;
    private JTextField rejestracja;
    private JTextField model;
    private JTextField marka;
    private JTextField predkosc_max;
    private JRadioButton manualna5BiegówRadioButton;
    private JRadioButton manualna6BiegówRadioButton;
    private JRadioButton benzynaRadioButton;
    private JRadioButton dieselRadioButton;
    private JButton dodajButton;
    private JButton anulujButton;
    private JLabel Nr_rej;
    private JLabel Model;
    private JLabel Marka;
    private JLabel Skrzynia;
    private JLabel Silnik;
    private JLabel Predkosc_max;

    public NowySamochodGUI(JComboBox comboBox){
        setContentPane(panel1);

        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkrzyniaBiegów skrzynia = new SkrzyniaBiegów(6, "6_biegowa", 350, 3000);
                Silnik silnik = new Silnik(6500, "Diesel", 200, 20000);
                if(manualna5BiegówRadioButton.isSelected()){
                    skrzynia.setIloscBiegow(5);
                    skrzynia.setNazwa("5_biegowa");
                }
                if(benzynaRadioButton.isSelected()){
                   silnik.setNazwa("Benzyna");
                }
                Pozycja aktPozycja_a = new Pozycja(0,0);
                Samochód samochod = new Samochód(false ,rejestracja.getText(), model.getText(), marka.getText(),
                        Double.valueOf(predkosc_max.getText()),aktPozycja_a,skrzynia, silnik);
                comboBox.addItem(samochod);
                comboBox.setRenderer(new MyObjectListCellRenderer());
                dispose();
            }
        });
    }
}
