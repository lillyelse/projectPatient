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



