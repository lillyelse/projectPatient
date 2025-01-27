package GUI;

import models.Patient;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PatientEditDialog extends JDialog {

    private JTextField vornameField, nachnameField, geburtsdatumField, strasseField, plzField, ortField,
            bundeslandField, geschlechtField, krankenkasseField, angehoerigerField;
    private Patient patient;
    private Patientendatenbank patientenDatenbank;
    private HauptGUI hauptGUI;

    public PatientEditDialog() {

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Felder initialisieren
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

        formPanel.add(new JLabel("Vorname:"));
        formPanel.add(vornameField);
        formPanel.add(new JLabel("Nachname:"));
        formPanel.add(nachnameField);
        formPanel.add(new JLabel("Geburtsdatum:"));
        formPanel.add(geburtsdatumField);
        formPanel.add(new JLabel("Straße:"));
        formPanel.add(strasseField);
        formPanel.add(new JLabel("PLZ:"));
        formPanel.add(plzField);
        formPanel.add(new JLabel("Ort:"));
        formPanel.add(ortField);
        formPanel.add(new JLabel("Bundesland:"));
        formPanel.add(bundeslandField);
        formPanel.add(new JLabel("GeschlechtID:"));
        formPanel.add(geschlechtField);
        formPanel.add(new JLabel("Krankenkasse:"));
        formPanel.add(krankenkasseField);
        formPanel.add(new JLabel("AngehörigerID:"));
        formPanel.add(angehoerigerField);

        add(formPanel, BorderLayout.CENTER);

        // Buttons für Speichern
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(e -> {
            try {
                savePatient();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Packen und Dialog zentrieren relativ zum übergeordneten Fenster
        pack();
        setLocationRelativeTo(getOwner());
    }

    // Methode, um den Patienten zu setzen, der bearbeitet werden soll
    public void setPatient(Patient patient, Patientendatenbank patientenDatenbank, HauptGUI hauptGUI) {
        this.patient = patient;
        this.patientenDatenbank = patientenDatenbank;
        this.hauptGUI = hauptGUI;

        // Initialisiere die Textfelder mit den aktuellen Patientendaten
        vornameField.setText(patient.getVorname());
        nachnameField.setText(patient.getNachname());
        geburtsdatumField.setText(patient.getGeburtsdatum().toString());
        strasseField.setText(patient.getStrasse());
        plzField.setText(patient.getPlz());
        ortField.setText(patient.getOrt());
        bundeslandField.setText(patient.getBundesland());
        geschlechtField.setText(String.valueOf(patient.getGeschlechtID()));
        krankenkasseField.setText(patient.getKrankenkasse());
        angehoerigerField.setText(String.valueOf(patient.getAngehoerigerID()));
    }

    // Speichert die bearbeiteten Patientendaten
    public void savePatient() throws SQLException {
        // Eingabewerte validieren und Patient mit neuen Daten aktualisieren
        patient.setVorname(vornameField.getText());
        patient.setNachname(nachnameField.getText());
        patient.setGeburtsdatum(java.sql.Date.valueOf(geburtsdatumField.getText()));
        patient.setStrasse(strasseField.getText());
        patient.setPlz(plzField.getText());
        patient.setOrt(ortField.getText());
        patient.setBundesland(bundeslandField.getText());
        patient.setGeschlechtID(Integer.parseInt(geschlechtField.getText()));
        patient.setKrankenkasse(krankenkasseField.getText());
        patient.setAngehoerigerID(Integer.parseInt(angehoerigerField.getText()));

        // Patient in der Datenbank aktualisieren
        if (patientenDatenbank.updatePatient(patient)) {
            JOptionPane.showMessageDialog(this, "Patient erfolgreich bearbeitet!");
            hauptGUI.refreshPatientTable();  // Patiententabelle im Hauptfenster aktualisieren
            dispose();  // Dialog schließen
        } else {
            JOptionPane.showMessageDialog(this, "Fehler beim Bearbeiten des Patienten!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

    }
}
