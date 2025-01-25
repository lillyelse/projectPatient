package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class HauptGUI extends JFrame {

        private Patientendatenbank patientenDatenbank;
        private JTable table;

        public HauptGUI(Patientendatenbank patientenDatenbank) {
            this.patientenDatenbank = patientenDatenbank;

            setTitle("Patientenverwaltung");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Menüleiste erstellen
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Patient");
            JMenuItem searchItem = new JMenuItem("Suchen");
            menu.add(searchItem);
            menuBar.add(menu);
            setJMenuBar(menuBar);

            // Aktionen für Menü
            searchItem.addActionListener(e -> searchPatient());

            // Zentralen Bereich (Hauptinhalt) hinzufügen
            JPanel centerPanel = new JPanel();
            centerPanel.add(new JLabel("Hauptbereich für die Patientenverwaltung"));
            add(centerPanel, BorderLayout.CENTER);



            // Tabelle mit Patienten anzeigen
            refreshPatientTable();

            setVisible(true);




        }



        private void searchPatient() {
            String patientIdStr = JOptionPane.showInputDialog(this, "Geben Sie die PatientID ein:");

            if (patientIdStr != null && !patientIdStr.isEmpty()) {
                try {
                    int patientId = Integer.parseInt(patientIdStr);
                    Patient patient = patientenDatenbank.getPatientById(patientId);

                    if (patient != null) {
                        // Falls der GUI.Patient gefunden wurde, Tabelle mit diesem Patienten aktualisieren
                        updatePatientTable(java.util.List.of(patient));
                    } else {
                        JOptionPane.showMessageDialog(this, "Kein GUI.Patient mit dieser ID gefunden!");
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


