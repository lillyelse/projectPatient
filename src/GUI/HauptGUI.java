package GUI;


import GUI.Patient.AddPatientFromMenu;
import GUI.Patient.DeletePatientFromMenu;
import GUI.Patient.EditPatientFromMenu;
import GUI.Patient.SearchPatientFromMenu;
import models.Patient; 
import database.DatenBankAnbindung;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Die Klasse HauptGUI erstellt die Haupt-Benutzeroberfläche.
 * Die Klasse erbt von JFrame.
 */
public class HauptGUI extends JFrame {

    private SearchPatientFromMenu searchPatientFromMenu;
    private AddPatientFromMenu addPatientFromMenu;
    private Patientendatenbank patientenDatenbank;
    private KontaktFormular kontaktFormular;
    private JTable table;

    /**
     * Konstruktor für die HauptGUI.
     * @param patientenDatenbank (wird für CRUD-Operationen verwendet)
     */
    public HauptGUI(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;

        // Erstelle das Haupt-Panel und setze das Layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialisiere das JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Erstelle eine Instanz der KontaktFormular-Klasse
        KontaktFormular kontaktFormularPanel = new KontaktFormular();

        // Füge die Tabs hinzu
        tabbedPane.addTab("Patiententabelle", createTablePanel()); // Hier kann man die Tabelle einfügen
        tabbedPane.addTab("Kontaktformular", kontaktFormularPanel); // Das Kontaktformular wird hier eingefügt

        // Füge das JTabbedPane zum mainPanel hinzu
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Setze das ContentPane auf das mainPanel
        setContentPane(mainPanel);

        setTitle("Patientenverwaltung");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Verhalten des JFrames, wenn der Benutzer das Fenster schließt/ Klicken auf das X
        setLocationRelativeTo(null);    //Position des Fensters. null: Fenster nicht abhängig von anderem, sondern einfach zentriert am Bildschirm


        // Menüleiste
        JMenuBar menuBar = new JMenuBar();
        // Pulldownmenü
        JMenu patientMenu = new JMenu("Patient");
        // Menüeinträge
        //JMenuItem searchItem = new JMenuItem("Suchen");
        JMenuItem addItem = new JMenuItem("Hinzufügen");
        JMenuItem editItem = new JMenuItem("Bearbeiten");
        JMenuItem deleteItem = new JMenuItem("Löschen");
        // Menüeinträge zum Pulldownmenü hinzufügen
        //patientMenu.add(searchItem);
        patientMenu.add(addItem);
        patientMenu.add(editItem);
        patientMenu.add(deleteItem);
        // Pulldownmenü zu Menüleiste hinzufügen
        menuBar.add(patientMenu);
        // sagt dem JFrame, dass es die Menüleiste menuBar verwenden soll.
        setJMenuBar(menuBar);

        // ActionListener für die Menüeinträge
        //searchItem.addActionListener(e -> searchPatientFromMenu.searchPatient());
        addItem.addActionListener(e -> addPatientFromMenu.addPatient());
        // die unteren beiden funktionieren noch nicht-noch nicht bearbeitet:
        editItem.addActionListener(e -> new EditPatientFromMenu(patientenDatenbank).execute(this));
        deleteItem.addActionListener(e -> new DeletePatientFromMenu(patientenDatenbank).execute(this));

        // ActionListener für Hauptmenü (Patient) funktionieren tun sie ??
        patientMenu.addMenuListener(new javax.swing.event.MenuListener() {
            @Override
            public void menuSelected(javax.swing.event.MenuEvent e) {
                // Tabelle mit allen Patienten aktualisieren
                refreshPatientTable();
            }

            @Override
            public void menuDeselected(javax.swing.event.MenuEvent e) {
                // Keine Aktion erforderlich
            }

            @Override
            public void menuCanceled(javax.swing.event.MenuEvent e) {
                // Keine Aktion erforderlich
            }
        });

        // Listener für das Schließen des Fensters
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatenBankAnbindung.close();
            }
        });

        refreshPatientTable();
        setVisible(true);
    }
    // Ende Konstruktor



    // METHODEN

    /**
     * Erstellt das Panel, das die Patiententabelle enthält.
     * @return Ein JScrollPane, das die Tabelle enthält.
     */
    private JScrollPane createTablePanel() {
        table = new JTable();
        refreshPatientTable();
        return new JScrollPane(table);
    }


    /**
     * Aktualisiert die Patiententabelle mit den aktuellen Daten aus der Datenbank.
     */
    public void refreshPatientTable() {
        try {
            java.util.List<Patient> patients = patientenDatenbank.getAllPatients();
            updatePatientTable((List) patients);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Abrufen der Patienten: " + e.getMessage());
        }
    }

    /**
     * Aktualisiert die Tabelle mit den übergebenen Patientendaten.
     * @param patients Eine Liste von Patienten, die in der Tabelle angezeigt werden sollen.
     */
    private void updatePatientTable(List<Patient> patients) {
        // Spaltennamen für die Tabelle
        String[] columnNames = {"PatientID", "Vorname", "Nachname", "Geburtsdatum", "Straße", "PLZ", "Ort", "Bundesland", "GeschlechtID", "Krankenkasse", "AngehoerigeID"};

        // Umwandlung der Patientenliste in ein 2D-Array für die Tabelle
        Object[][] data = patients.stream()
                .map(patient -> new Object[]{
                        patient.getPatientid(),       // Zugriff auf Instanzmethoden
                        patient.getVorname(),
                        patient.getNachname(),
                        patient.getGeburtsdatum(),
                        patient.getStrasse(),
                        patient.getPlz(),
                        patient.getOrt(),
                        patient.getBundesland(),
                        patient.getGeschlechtID(),
                        patient.getKrankenkasse(),
                        patient.getAngehoerigerID()
                })
                .toArray(Object[][]::new); // Umwandlung des Streams in ein 2D-Array

        // Setze das Modell der JTable mit den Daten und Spaltennamen
        table.setModel(new DefaultTableModel(data, columnNames));
    }
}





