package GUI.Patient;

import GUI.Patientendatenbank;
import models.Patient;     //Models -> Patient geplant

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class AddPatientFromMenu {

    private Patientendatenbank patientenDatenbank;


    public AddPatientFromMenu(Patientendatenbank patientenDatenbank) {
            this.patientenDatenbank = patientenDatenbank;
        }


    public void addPatient() {
            // Panel für die Eingabefelder
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Textfelder-Objekte erstellen
            JTextField vornameField = new JTextField(20);
            JTextField nachnameField = new JTextField(20);
            JTextField geburtsdatumField = new JTextField(20);
            JTextField strasseField = new JTextField(20);
            JTextField plzField = new JTextField(20);
            JTextField ortField = new JTextField(20);
            JTextField bundeslandField = new JTextField(20);
            JTextField geschlechtField = new JTextField(20);
            JTextField krankenkasseField = new JTextField(20);
            JTextField angehoerigerField = new JTextField(20);

            // diese Textfelder und neue Label-Objekte zum Panel hinzufügen
            panel.add(new JLabel("Vorname:"));
            panel.add(vornameField);
            panel.add(new JLabel("Nachname:"));
            panel.add(nachnameField);
            panel.add(new JLabel("Geburtsdatum (yyyy-MM-dd):"));
            panel.add(geburtsdatumField);
            panel.add(new JLabel("Straße:"));
            panel.add(strasseField);
            panel.add(new JLabel("PLZ:"));
            panel.add(plzField);
            panel.add(new JLabel("Ort:"));
            panel.add(ortField);
            panel.add(new JLabel("Bundesland:"));
            panel.add(bundeslandField);
            panel.add(new JLabel("Geschlecht ID:"));
            panel.add(geschlechtField);
            panel.add(new JLabel("Krankenkasse:"));
            panel.add(krankenkasseField);
            panel.add(new JLabel("Angehöriger ID:"));
            panel.add(angehoerigerField);


        // Bestätigungs-Dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Neuen Patienten hinzufügen", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Eingabefelder überprüfen
            if (vornameField.getText().trim().isEmpty() || nachnameField.getText().trim().isEmpty() ||
                    geburtsdatumField.getText().trim().isEmpty() || strasseField.getText().trim().isEmpty() ||
                    plzField.getText().trim().isEmpty() || ortField.getText().trim().isEmpty() ||
                    bundeslandField.getText().trim().isEmpty() || geschlechtField.getText().trim().isEmpty() ||
                    krankenkasseField.getText().trim().isEmpty() || angehoerigerField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return; // Abbruch der Methode, wenn ein Feld leer ist
            }

            try {
                // Geburtsdatum validieren und umwandeln (Date-Objekt)
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = formatter.parse(geburtsdatumField.getText().trim()); // java.util.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Konvertierung zu java.sql.Date

                // Geschlecht und Angehörige prüfen
                int geschlecht = Integer.parseInt(geschlechtField.getText().trim());
                int angehoerige = Integer.parseInt(angehoerigerField.getText().trim());

                // Neues Patientenobjekt erstellen
                Patient patient = new Patient(
                        -1, // PatientenID wird später von der DB vergeben
                        vornameField.getText().trim(),
                        nachnameField.getText().trim(),
                        sqlDate, // Geburtsdatum als java.sql.Date
                        strasseField.getText().trim(),
                        plzField.getText().trim(),
                        ortField.getText().trim(),
                        bundeslandField.getText().trim(),
                        geschlecht,
                        krankenkasseField.getText().trim(),
                        angehoerige
                );

                // Patient zur Datenbank hinzufügen
                boolean success = patientenDatenbank.addPatient(patient);

                // Erfolgsmeldung anzeigen
                if (success) {
                    JOptionPane.showMessageDialog(null, "Patient erfolgreich hinzugefügt!");
                } else {
                    JOptionPane.showMessageDialog(null, "Fehler beim Hinzufügen des Patienten.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Ungültiges Geburtsdatum! Bitte im Format yyyy-MM-dd eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe bei Geschlecht oder Angehörigen. Bitte Zahlen eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
}


