package GUI;
import models.Patient;
import javax.swing.*;
import java.sql.SQLException;

//Klasse, die sich darum kümmert Patient hinzuzufügen, wissen noch nicht, ob wir sie brauchen

public class PatientManager {

    private Patientendatenbank patientenDatenbank;
    private HauptGUI hauptGUI;
    private KontaktFormular kontaktFormular;

    public PatientManager(Patientendatenbank patientenDatenbank, HauptGUI hauptGUI) {
        this.patientenDatenbank = patientenDatenbank;
        this.hauptGUI = hauptGUI;
    }

    //doch nicht benutzt?
    public void addPatient() throws SQLException {
        // Schritt 1: Erstelle das Patient-Objekt aus den Eingabefeldern
        Patient patient = kontaktFormular.createPatientFromFields();  // Diese Methode erstellt das Patient-Objekt basierend auf den Feldern

        // Überprüfen, ob das Patient-Objekt null ist, bevor mit der Datenbankoperation fortgefahren wird
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "Patient konnte nicht erstellt werden. Bitte überprüfen Sie die Eingaben.");
            return;
        }

        // Schritt 2: Versuche, den Patienten zur Datenbank hinzuzufügen
        boolean success = (boolean) patientenDatenbank.addPatient(patient);

        // Schritt 3: Zeige eine Erfolgsmeldung, falls der Patient erfolgreich hinzugefügt wurde
        if (success) {
            JOptionPane.showMessageDialog(null, "Patient erfolgreich hinzugefügt!");
            hauptGUI.refreshPatientTable();  // Aktualisiere die Patiententabelle
        } else {
            // Fehlerfall: Zeige eine Fehlermeldung, wenn das Hinzufügen des Patienten nicht erfolgreich war
            JOptionPane.showMessageDialog(null, "Fehler beim Hinzufügen des Patienten. Bitte versuchen Sie es erneut.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }


}
