package sk.tmconsulting.evidenciavydavkov.view;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class GUI {
    private static int indexVydavku;

    public static void main(String[] args) {
        JFrame hlavneOkno = new JFrame("Evidencia výdavkov");
        hlavneOkno.setMinimumSize(new Dimension(800, 600));
        hlavneOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ked kliknem na X na okne (cize vo hlavneOkno) tak sa zatvori standardne
        hlavneOkno.setLocationRelativeTo(null); // vycentrovanie okna


        // JFrame by mal obsahovat panel, teda container JPanel
        JPanel panel = new JPanel();
        panel.setLayout(null); // layout pre panel bude null, cize prazdny
        
        hlavneOkno.setContentPane(panel); // Dany panel pridame do hlavneOkno
        //hlavneOkno.add(panel);


        // Nazov vydavku
        JLabel lblNazovVydavku = new JLabel("Názov výdavku"); // popisok
        lblNazovVydavku.setBounds(85, 65, 100, 20); // x, y, sirka, vyska
        panel.add(lblNazovVydavku);

        JTextField txfNazovVydavku = new JTextField();
        txfNazovVydavku.setBounds(190, 60, 200, 30); // x, y, sirka, vyska
        panel.add(txfNazovVydavku);


        // Cena vydavku
        JLabel lblCenaVydavku = new JLabel("Cena výdavku"); // popisok
        lblCenaVydavku.setBounds(85, 95, 100, 20); // x, y, sirka, vyska
        panel.add(lblCenaVydavku);

        JTextField txfCenaVydavku = new JTextField();
        txfCenaVydavku.setBounds(190, 90, 200, 30); // x, y, sirka, vyska
        panel.add(txfCenaVydavku);


        // Kategoria vydavku
        JLabel lblKategorie = new JLabel("Kategória"); // popisok
        lblKategorie.setBounds(85, 125, 100, 20); // x, y, sirka, vyska
        panel.add(lblKategorie);

        String[] kategorie = { "POTRAVINY", "PHM", "INÉ", "OBLEČENIE", "KONÍČKY" };
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox cmbKategorie = new JComboBox(kategorie);
        cmbKategorie.setSelectedIndex(4);
        cmbKategorie.setBounds(190, 120, 200, 30); // x, y, sirka, vyska
        panel.add(cmbKategorie);


        // Datum
        JLabel lblDatum = new JLabel("Dátum"); // popisok
        lblDatum.setBounds(85, 155, 100, 20); // x, y, sirka, vyska
        panel.add(lblDatum);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(190, 150, 200, 30);

        panel.add(datePicker);


        // Zoznam vydavkov, label
        JLabel lblZoznamVydavkov = new JLabel("Zoznam výdavkov"); // popisok
        lblZoznamVydavkov.setBounds(420, 35, 150, 20); // x, y, sirka, vyska
        panel.add(lblZoznamVydavkov);

        // Zoznam vydavkov, list
        DefaultListModel modelZoznamu = new DefaultListModel<>(); // Vytvorenie modelu zoznamu
        JList lstZoznamVydavkov = new JList(modelZoznamu);
        // Pridanie predvyplnenych udajov
        modelZoznamu.addElement("Chlieb 2.3 POTRAVINY 27.9.2023");
        modelZoznamu.addElement("Rožky 1 POTRAVINY 23.9.2023");
        lstZoznamVydavkov.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {
                        String vybranyVydavok = lstZoznamVydavkov.getSelectedValue().toString();
                        //String regex = "(?<=\\d\\.\\s\\d\\.\\s\\d{4})\\s+";
                        String regex = " "; // To je znak podla ktoreho rozdelujeme text na jednotlive casti, v nasom pripade medzera
                        String jednotliveUdajeVydavku[] = vybranyVydavok.split(regex); // Po rozdeleni nam vznikne pole s jednotlivymi udajmi

                        // Naplnime jednotlive textfields a dalsie komponenty
                        // Ukazka dat: Chlieb 2.3 POTRAVINY 27.9.2023
                        txfNazovVydavku.setText(jednotliveUdajeVydavku[0]);
                        txfCenaVydavku.setText(jednotliveUdajeVydavku[1]);
                        cmbKategorie.setSelectedItem(jednotliveUdajeVydavku[2]);
                        datePicker.getJFormattedTextField().setText(jednotliveUdajeVydavku[3]);

                        indexVydavku = lstZoznamVydavkov.getSelectedIndex();

                        System.out.println(Arrays.toString(jednotliveUdajeVydavku)); // Vypiseme jednotlive prvky pola
                        System.out.println(vybranyVydavok);
                    } catch (NullPointerException e1) {
                        // TODO Spracovat
                    }
                }
            }
        });

        // Zoznam vydavkov, scrollbar
        JScrollPane scbZoznamVydavkov = new JScrollPane(lstZoznamVydavkov);
        scbZoznamVydavkov.setBounds(420, 65, 330, 300);
        panel.add(scbZoznamVydavkov);


        // Tlacidlo na pridanie zaznamu
        JButton btnPridajZaznam = new JButton("Pridaj záznam");
        btnPridajZaznam.setBounds(85, 405, 120, 30);
        btnPridajZaznam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Po kliknuti na tlacidlo musime doprogramovat ...
                modelZoznamu.addElement(txfNazovVydavku.getText() + " " + txfCenaVydavku.getText() + " " + cmbKategorie.getSelectedItem() + " " + datePicker.getJFormattedTextField().getText().replace(" ", ""));
            }
        });
        panel.add(btnPridajZaznam);


        // Tlacidlo na upravu zaznamu
        JButton btnUpravZaznam = new JButton("Ulož úpravu");
        btnUpravZaznam.setBounds(225, 405, 120, 30);
        btnUpravZaznam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Po kliknuti na tlacidlo musime doprogramovat ...
                modelZoznamu.setElementAt(txfNazovVydavku.getText() + " " + txfCenaVydavku.getText() + " " + cmbKategorie.getSelectedItem() + " " + datePicker.getJFormattedTextField().getText().replace(" ", ""), indexVydavku);
                //lstZoznamVydavkov.setIn
            }
        });
        panel.add(btnUpravZaznam);


        // Tlacidlo na odstranenie zaznamu
        JButton btnOdstranZaznam = new JButton("Odstráň záznam");
        btnOdstranZaznam.setBounds(365, 405, 130, 30);
        btnOdstranZaznam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Po kliknuti na tlacidlo musime doprogramovat ...
                modelZoznamu.remove(indexVydavku);
            }
        });
        panel.add(btnOdstranZaznam);


        // Tlacidlo na ukoncenie aplikacie
        JButton btnUkonciAplikaciu = new JButton("Ukonči aplikáciu");
        btnUkonciAplikaciu.setBounds(515, 405, 130, 30);
        btnUkonciAplikaciu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Po kliknuti na tlacidlo musime doprogramovat ...
                System.exit(0);
            }
        });
        panel.add(btnUkonciAplikaciu);

/*        JButton btnOk = new JButton("Vypiš!"); // tlacidlo
        btnOk.setBounds(100, 100, 80, 20); // x, y, sirka, vyska
        panel.add(btnOk);

        btnOk.addActionListener(new ActionListener() { // sluzi na "odchytenie" cize spracovanie zatlacenia tlacidla
            public void actionPerformed(ActionEvent e) {
                // Aplikacna logika po stlaceni na tlacidlo
*//*                System.out.println(txfCenaVydavku.getText());
                lblNazovVydavku.setText( txfCenaVydavku.getText() );*//*
                System.out.println("Stlačil som tlačidlo Vypíš");
                System.out.println(txfCenaVydavku.getText()); // Vypise obsah txfCenaVydavku do konzoly
                lblNazovVydavku.setText(txfCenaVydavku.getText());
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(210, 100, 80, 20);
        panel.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hlavneOkno.dispatchEvent(new WindowEvent(hlavneOkno, WindowEvent.WINDOW_CLOSING)); // Korektne zatvori celu SWING aplikaciu
            }
        });*/

/*        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hlavneOkno.dispatchEvent(new WindowEvent(hlavneOkno, WindowEvent.WINDOW_CLOSING)); // Korektne zatvori celu SWING aplikaciu
            }
        });*/



        // display it
        hlavneOkno.pack();
        hlavneOkno.setVisible(true);

    }
}
