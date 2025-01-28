package GUI;
import models.Patient;
import javax.swing.*;
import java.sql.SQLException;

//Klasse, die sich darum kümmert Patient hinzuzufügen, wissen noch nicht, ob wir sie brauchen

/**
 * Diese Klasse dient als Manager für Patientendaten
 * Sie stellt Funktionen bereit,um Patienten zu verwalten
 */
public class PatientManager {

    private Patientendatenbank patientenDatenbank;
    private HauptGUI hauptGUI;
    private KontaktFormular kontaktFormular;

    /**
     * Konstruktor für die Klasse PatientManager.
     * Initialisiert die Patientendatenbank und die Haupt-GUI.
     * @param patientenDatenbank Die Instanz der Patientendatenbank für den Zugriff auf die Patientendaten.
     * @param hauptGUI Die Haupt-GUI für Interaktionen mit der Benutzeroberfläche.
     */
    public PatientManager(Patientendatenbank patientenDatenbank, HauptGUI hauptGUI) {
        this.patientenDatenbank = patientenDatenbank;
        this.hauptGUI = hauptGUI;
    }

    //doch nicht benutzt?:

    /**
     * Fügt eine:n neue:n Patient:in zur Datenbank hinzu.
     * @param patient Das Patient-Objekt, das hinzugefügt werden soll.
     * @return true, wenn Patient:in erfolgreich hinzugefügt wurde, andernfalls false
     * @throws SQLException Wenn ein Fehler bei der Datenbankoperation auftritt.
     */
    public boolean addPatient(Patient patient) throws SQLException {
        // Überprüfen, ob das Patient-Objekt null ist, bevor mit der Datenbankoperation fortgefahren wird
        if (patient == null) {
            throw new IllegalStateException("Patient konnte nicht erstellt werden. Bitte überprüfen Sie die Eingaben.");
        }

        // Hier wird der Patient zur Datenbank hinzugefügt, Beispiel für den Datenbankzugriff:
        // Wir gehen davon aus, dass patientenDatenbank.addPatient(patient) eine SQLException werfen kann.
        boolean success = patientenDatenbank.addPatient(patient); // true oder false je nach Erfolg

        // Rückgabe des Erfolgsstatus
        return success;
    }
    }



