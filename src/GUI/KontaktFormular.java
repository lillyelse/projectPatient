package GUI;

import models.Patient;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class KontaktFormular extends JPanel {

    private JPanel kontaktFormularPanel;
    private JTextField vornameField, nachnameField, geburtsdatumField, strasseField, plzField, ortField,
            bundeslandField, geschlechtField, krankenkasseField, angehoerigerField;
    private PatientManager patientManager;
    private Patientendatenbank patientenDatenbank;
    private HauptGUI hauptGUI;


    public KontaktFormular() {
        this.kontaktFormularPanel = new JPanel(new BorderLayout());


        // Initialisiere das Kontaktformular Panel
        JPanel kontaktFormularPanel = new JPanel(new BorderLayout());
        kontaktFormularPanel.setBorder(BorderFactory.createTitledBorder("Kontaktformular"));

        // Panel für das Formular
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Felder für die Eingabe erstellen
        vornameField = new JTextField();
        nachnameField = new JTextField();
        geburtsdatumField = new JTextField();
        strasseField = new JTextField();
        plzField = new JTextField();
        ortField = new JTextField();
        bundeslandField = new JTextField();
        geschlechtField = new JTextField();
        krankenkasseField = new JTextField();
        angehoerigerField = new JTextField();

        // mit formPanel.add Labels und Felder zum Formular hinzugefügt
        formPanel.add(new JLabel("Vorname:"));
        formPanel.add(vornameField);
        formPanel.add(new JLabel("Nachname:"));
        formPanel.add(nachnameField);
        formPanel.add(new JLabel("Geburtsdatum (YYYY-MM-DD):"));
        formPanel.add(geburtsdatumField);
        formPanel.add(new JLabel("Straße:"));
        formPanel.add(strasseField);
        formPanel.add(new JLabel("PLZ:"));
        formPanel.add(plzField);
        formPanel.add(new JLabel("Ort:"));
        formPanel.add(ortField);
        formPanel.add(new JLabel("Bundesland:"));
        formPanel.add(bundeslandField);
        formPanel.add(new JLabel("Geschlecht ID:"));
        formPanel.add(geschlechtField);
        formPanel.add(new JLabel("Krankenkasse:"));
        formPanel.add(krankenkasseField);
        formPanel.add(new JLabel("Angehörige ID:"));
        formPanel.add(angehoerigerField);

        // Kontaktformular Layout zum Panel hinzugefügt
        kontaktFormularPanel.add(formPanel, BorderLayout.CENTER);

        // Button Panel erstellen
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Buttons erstellen
        JButton hinzufuegenButton = new JButton("Hinzufügen");
        JButton bearbeitenButton = new JButton("Bearbeiten");
        JButton loeschenButton = new JButton("Löschen");

        // ActionListener für Buttons hinzufügen!!!
        hinzufuegenButton.addActionListener(e -> {
            try {
                // Schritt 1: Patient aus den Eingabefeldern erstellen
                Patient patient = createPatientFromFields();

                if (patient == null) {
                    JOptionPane.showMessageDialog(null, "Fehler bei der Eingabe der Patientendaten. Bitte überprüfen Sie die Felder.",
                            "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                    return;  // Rückgabe, falls die Patientenerstellung fehlgeschlagen ist
                }

                // Schritt 2: Versuchen, den Patienten zur Datenbank hinzuzufügen
                boolean success = (boolean) patientenDatenbank.addPatient(patient);

                if (success) {
                    // Erfolgsmeldung, wenn der Patient erfolgreich hinzugefügt wurde
                    JOptionPane.showMessageDialog(null, "Patient erfolgreich hinzugefügt!");
                    hauptGUI.refreshPatientTable(); // Patiententabelle aktualisieren
                    clearFields(); // Eingabefelder leeren
                } else {
                    // Fehlerfall: Zeige eine Fehlermeldung, wenn das Hinzufügen nicht erfolgreich war
                    JOptionPane.showMessageDialog(null, "Fehler beim Hinzufügen des Patienten. Bitte versuchen Sie es erneut.",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                // Allgemeine Fehlerbehandlung, falls andere unerwartete Fehler auftreten
                JOptionPane.showMessageDialog(null, "Unerwarteter Fehler: " + ex.getMessage(),
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        bearbeitenButton.addActionListener(e -> new PatientEditDialog());
        loeschenButton.addActionListener(e -> deletePatient());

        // Füge die Buttons zum Button Panel hinzu
        buttonPanel.add(hinzufuegenButton);
        buttonPanel.add(bearbeitenButton);
        buttonPanel.add(loeschenButton);

        // Drei Buttons zum Panel vom Kontaktformular Panel hinzugefügt
        kontaktFormularPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Setze das Kontaktformular Panel als das Hauptpanel des Kontaktformulars
        this.setLayout(new BorderLayout());
        this.add(kontaktFormularPanel, BorderLayout.CENTER);

    }

    private void deletePatient() {
        String patientIdStr = JOptionPane.showInputDialog(this, "Geben Sie die PatientID ein, die Sie löschen möchten:");
        if (patientIdStr != null && !patientIdStr.isEmpty()) {
            try {
                int patientId = Integer.parseInt(patientIdStr);
                Patient patient = patientenDatenbank.getPatientById(patientId);
                if (patient != null) {
                    int confirmation = JOptionPane.showConfirmDialog(this,
                            "Möchten Sie den Patienten mit der ID " + patientId + " wirklich löschen?",
                            "Bestätigung", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        if (patientenDatenbank.deletePatient(patientId)) {
                            JOptionPane.showMessageDialog(this, "Patient erfolgreich gelöscht!");
                            hauptGUI.refreshPatientTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Fehler beim Löschen des Patienten!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Kein Patient mit dieser ID gefunden!");
                }
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
            }
        }
    }
    public Patient createPatientFromFields() {
        try {
            String vorname = vornameField.getText().trim();
            String nachname = nachnameField.getText().trim();
            String geburtsdatumStr = geburtsdatumField.getText().trim();
            String strasse = strasseField.getText().trim();
            String plz = plzField.getText().trim();
            String ort = ortField.getText().trim();
            String bundesland = bundeslandField.getText().trim();
            int geschlecht = Integer.parseInt(geschlechtField.getText().trim());
            String krankenkasse = krankenkasseField.getText().trim();
            int angehoeriger = Integer.parseInt(angehoerigerField.getText().trim());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = formatter.parse(geburtsdatumStr);
            java.sql.Date geburtsdatum = new java.sql.Date(utilDate.getTime());

            return new Patient(0, vorname, nachname, geburtsdatum, strasse, plz, ort, bundesland, geschlecht, krankenkasse, angehoeriger);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler bei der Eingabe: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    //funktioniert noch nicht, soll mein Kontaktformular leeren, nachdem ich es ausgefüllt hab
    public void clearFields() {
        vornameField.setText("");
        nachnameField.setText("");
        geburtsdatumField.setText("");
        strasseField.setText("");
        plzField.setText("");
        ortField.setText("");
        bundeslandField.setText("");
        geschlechtField.setText("");
        krankenkasseField.setText("");
        angehoerigerField.setText("");
    }
}
