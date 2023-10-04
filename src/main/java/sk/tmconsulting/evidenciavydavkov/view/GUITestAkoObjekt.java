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

public class GUITestAkoObjekt {
    private int indexVydavku;

    public GUITestAkoObjekt() {
        zobrazHlavneOkno();
    }

    public void zobrazHlavneOkno() {
        JFrame hlavneOkno = new JFrame("Evidencia výdavkov by Ján Žitniak");
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
                        indexVydavku = lstZoznamVydavkov.getSelectedIndex();
                        vyplnPolozky(modelZoznamu, txfNazovVydavku, txfCenaVydavku, cmbKategorie, datePicker);
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
                zobrazDialog(hlavneOkno, modelZoznamu, true);
            }
        });
        panel.add(btnPridajZaznam);


        // Tlacidlo na upravu zaznamu
        JButton btnUpravZaznam = new JButton("Uprav záznam");
        btnUpravZaznam.setBounds(225, 405, 120, 30);
        btnUpravZaznam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Po kliknuti na tlacidlo musime doprogramovat ...
                zobrazDialog(hlavneOkno, modelZoznamu, false);
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

        // display it
        hlavneOkno.pack();
        hlavneOkno.setVisible(true);
    }

    // Parameter novyZaznam je vlastne prepinac medzi dialogmi pre novy zaznam a upravu zaznamu
    private  void zobrazDialog(JFrame hlavneOkno, DefaultListModel modelZoznamu, boolean novyZaznam) {
        /*                modelZoznamu.addElement(txfNazovVydavku.getText() + " " + txfCenaVydavku.getText() + " " + cmbKategorie.getSelectedItem() + " " + datePicker.getJFormattedTextField().getText().replace(" ", ""));*/
        JDialog jDialog = new JDialog(hlavneOkno, true); // Do JDialogu vkladame parametre ako su jFrame a druhy je nastavenie pre modalne zobrazenie okna
        jDialog.setMinimumSize(new Dimension(380, 220));
        jDialog.setResizable(false); // Zakazame zmenu rozmerov dialogu
        jDialog.setLocationRelativeTo(null); // vycentrovanie okna


        // JFrame by mal obsahovat panel, teda container JPanel
        JPanel panel = new JPanel();
        panel.setLayout(null); // layout pre panel bude null, cize prazdny

        int x = 10, y = 10;

        // Formularove komponenty jDialogu
        // Nazov vydavku
        JLabel lblNazovVydavku = new JLabel("Názov výdavku"); // popisok
        lblNazovVydavku.setBounds(x, y, 100, 20); // x, y, sirka, vyska
        panel.add(lblNazovVydavku);

        JTextField txfNazovVydavku = new JTextField();
        txfNazovVydavku.setBounds(x + 105, y-5, 200, 30); // x, y, sirka, vyska
        panel.add(txfNazovVydavku);


        // Cena vydavku
        JLabel lblCenaVydavku = new JLabel("Cena výdavku"); // popisok
        lblCenaVydavku.setBounds(x, y + 35, 100, 20); // x, y, sirka, vyska
        panel.add(lblCenaVydavku);

        JTextField txfCenaVydavku = new JTextField();
        txfCenaVydavku.setBounds(x + 105, y + 30, 200, 30); // x, y, sirka, vyska
        panel.add(txfCenaVydavku);


        // Kategoria vydavku
        JLabel lblKategorie = new JLabel("Kategória"); // popisok
        lblKategorie.setBounds(x, y + 70, 100, 20); // x, y, sirka, vyska
        panel.add(lblKategorie);

        String[] kategorie = { "POTRAVINY", "PHM", "INÉ", "OBLEČENIE", "KONÍČKY" };
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox cmbKategorie = new JComboBox(kategorie);
        cmbKategorie.setSelectedIndex(4);
        cmbKategorie.setBounds(x + 105, y + 65, 200, 30); // x, y, sirka, vyska
        panel.add(cmbKategorie);


        // Datum
        JLabel lblDatum = new JLabel("Dátum"); // popisok
        lblDatum.setBounds(x, y + 105, 100, 20); // x, y, sirka, vyska
        panel.add(lblDatum);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(x + 105, y + 100, 200, 30);

        panel.add(datePicker);

        // Tlacidlo na ulozenie noveho zaznamu
        JButton btnDialogUloz = new JButton("Ulož");
        btnDialogUloz.setBounds(x + 105, y + 140, 120, 30);
        panel.add(btnDialogUloz);

        btnDialogUloz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (novyZaznam) {
                    modelZoznamu.addElement(txfNazovVydavku.getText() + " " + txfCenaVydavku.getText() + " " + cmbKategorie.getSelectedItem() + " " + datePicker.getJFormattedTextField().getText().replace(" ", ""));
                } else {
                    // TODO naplnime aktualny zaznam upravenymi hodnotami
                    modelZoznamu.setElementAt(txfNazovVydavku.getText() + " " + txfCenaVydavku.getText() + " " + cmbKategorie.getSelectedItem() + " " + datePicker.getJFormattedTextField().getText().replace(" ", ""), indexVydavku);
                }
                jDialog.dispose();
            }
        });

        if (!novyZaznam) {
            vyplnPolozky(modelZoznamu, txfNazovVydavku, txfCenaVydavku, cmbKategorie, datePicker);
        }


        jDialog.setContentPane(panel); // Dany panel pridame do jDialog
        jDialog.pack(); // "Rozbalenie" obsahu, resp. komponentov v JDialogu do pozeratelnej podoby
        jDialog.setVisible(true);
    }

    private  void vyplnPolozky(DefaultListModel modelZoznamu, JTextField txfNazovVydavku, JTextField txfCenaVydavku, JComboBox cmbKategorie, JDatePickerImpl datePicker) {
        String vybranyVydavok = modelZoznamu.getElementAt(indexVydavku).toString();
        // String vybranyVydavok = lstZoznamVydavkov.getSelectedValue().toString();
        //String regex = "(?<=\\d\\.\\s\\d\\.\\s\\d{4})\\s+";
        String regex = " "; // To je znak podla ktoreho rozdelujeme text na jednotlive casti, v nasom pripade medzera
        String jednotliveUdajeVydavku[] = vybranyVydavok.split(regex); // Po rozdeleni nam vznikne pole s jednotlivymi udajmi

        // Naplnime jednotlive textfields a dalsie komponenty
        // Ukazka dat: Chlieb 2.3 POTRAVINY 27.9.2023
        txfNazovVydavku.setText(jednotliveUdajeVydavku[0]);
        txfCenaVydavku.setText(jednotliveUdajeVydavku[1]);
        cmbKategorie.setSelectedItem(jednotliveUdajeVydavku[2]);
        datePicker.getJFormattedTextField().setText(jednotliveUdajeVydavku[3]);
    }
}
