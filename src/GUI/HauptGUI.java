package GUI;

import database.DatenBankAnbindung;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class HauptGUI extends JFrame {

    private SearchPatientFromMenu searchPatientFromMenu;            //geplant
    private AddPatientFromMenu addPatientFromMenu;                 //geplant
    private Patientendatenbank patientenDatenbank;
    private KontaktFormular kontaktFormular;                        //geplant
    private JTable table;

    public HauptGUI(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;


        // Erstelle das Haupt-Panel und setze das Layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialisiere das JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Erstelle eine Instanz der KontaktFormular-Klasse
        KontaktFormular kontaktFormularPanel = new KontaktFormular();

        // Füge die Tabs hinzu
        tabbedPane.addTab("Patiententabelle", createTablePanel()); // Hier kannst du die Tabelle einfügen
        tabbedPane.addTab("Kontaktformular", kontaktFormularPanel); // Das Kontaktformular wird hier eingefügt

        // Füge das JTabbedPane zum mainPanel hinzu
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Setze das ContentPane auf das mainPanel
        setContentPane(mainPanel);

        setTitle("Patientenverwaltung");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        JMenuBar menuBar = new JMenuBar();
        JMenu patientMenu = new JMenu("Patient");

        JMenuItem searchItem = new JMenuItem("Suchen");
        JMenuItem addItem = new JMenuItem("Hinzufügen");
        JMenuItem editItem = new JMenuItem("Bearbeiten");
        JMenuItem deleteItem = new JMenuItem("Löschen");

        patientMenu.add(searchItem);
        patientMenu.add(addItem);
        patientMenu.add(editItem);
        patientMenu.add(deleteItem);

        menuBar.add(patientMenu);
        setJMenuBar(menuBar);

        searchItem.addActionListener(e -> searchPatientFromMenu.searchPatient());
        addItem.addActionListener(e -> addPatientFromMenu.addPatient());
        //unteren beiden funktionieren noch nicht-noch nicht bearbeitet
        editItem.addActionListener(e -> new EditPatientFromMenu(patientenDatenbank).execute(this));
        deleteItem.addActionListener(e -> new DeletePatientFromMenu(patientenDatenbank).execute(this));


        // ActionListener für Hauptmenü (Patient)
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatenBankAnbindung.close();
            }
        });

        refreshPatientTable();
        setVisible(true);
    }
        }











        private void searchPatient() {
            String patientIdStr = JOptionPane.showInputDialog(this, "Geben Sie die PatientID ein:");

            if (patientIdStr != null && !patientIdStr.isEmpty()) {
                try {
                    int patientId = Integer.parseInt(patientIdStr);
                    Patient patient = patientenDatenbank.getPatientById(patientId);

                    if (patient != null) {
                        // Falls der GUI.GUI.models.Patient gefunden wurde, Tabelle mit diesem Patienten aktualisieren
                        updatePatientTable(java.util.List.of(patient));
                    } else {
                        JOptionPane.showMessageDialog(this, "Kein GUI.GUI.models.Patient mit dieser ID gefunden!");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
                }
            }
        }



        private void refreshPatientTable() {
            try {
                java.util.List<Patient> patients = patientenDatenbank.getAllPatients(); // Alle Patienten anzeigen
                updatePatientTable(patients);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Fehler beim Laden der Patienten: " + e.getMessage());
            }
        }

        private void updatePatientTable(List<Patient> patients) {
            String[] columnNames = {"PatientID", "Vorname", "Nachname", "Geburtsdatum", "Strasse", "PLZ", "Ort", "Bundesland", "Geschlecht", "Krankenkasse", "Angehoerige"};
            Object[][] data = patients.stream()
                    .map(patient -> new Object[]{
                            patient.getpatientid(),
                            patient.getVorname(),
                            patient.getNachname(),
                            patient.getGeburtsdatum(),
                            patient.getStrasse(),
                            patient.getPlz(),
                            patient.getOrt(),
                            patient.getBundesland(),
                            patient.getGeschlechtID(),
                            patient.getKrankenkasse(),
                            patient.getAngehoerigeID()
                    }).toArray(Object[][]::new);

            table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);

            getContentPane().removeAll(); // Inhalt des Fensters löschen
            add(scrollPane, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }


