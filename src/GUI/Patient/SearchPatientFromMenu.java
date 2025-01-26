package GUI.Patient;// GUI.Patient.SearchPatientFromMenu.java
import GUI.HauptGUI;
import GUI.Patientendatenbank;
import models.Patient;
import javax.swing.*;

/**
 * Die Klasse SearchPatientFromMenu stellt eine Benutzeroberfläche zur Verfügung,
 * um nach eine:r Patient:in anhand der PatientID zu suchen.
 * Der:die Benutzer:in wird aufgefordert, eine PatientID einzugeben, und die Klasse versucht dann,
 *  den:die Patient:in in der Patientendatenbank zu finden.
 *  Wenn erfolgreich, werden die Patientendaten angezeigt,
 *  ansonsten wird eine Fehlermeldung ausgegeben.
 */
public class SearchPatientFromMenu {

    // ATTRIBUT
    private Patientendatenbank patientenDatenbank;

    /**
     * Konstruktor der Klasse SearchPatientFromMenu.
     * @param patientenDatenbank Die Patientendatenbank, in der nach Patient:innen gesucht wird.
     */
    public SearchPatientFromMenu(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;
    }


    //METHODEN

    /**
     * Führt den Suchvorgang für eine:n Patient:in anhand der eingegebenen PatientID durch.
     * Zeigt ein Eingabefenster an, in dem die PatientID eingegeben werden kann.
     * Wenn ein:e Patient:in mit dieser ID gefunden wird, werden die Patientendaten angezeigt.
     * Wenn ungültige ID eingegeben wird oder die Eingabe leer bleibt,
     * wird eine entsprechende Fehlermeldung angezeigt.
     */
    public void searchPatient() {
        // Zeigt ein Dialogfenster an, in dem die PatientID eingegeben werden kann.
        String patientIdStr = JOptionPane.showInputDialog(null, "Geben Sie die PatientID ein:");
        // Überprüft, ob die Eingabe gültig ist
        if (patientIdStr != null && !patientIdStr.isEmpty()) {
            try {
                // Versucht, die eingegebene PatientID in eine integer-Zahl umzuwandeln
                int patientId = Integer.parseInt(patientIdStr);
                // Holt den:die Patient:in anhand der PatientID aus der Datenbank
                Patient patient = patientenDatenbank.getPatientById(patientId);
                // Wenn Patient:in gefunden, zeigt es die Patientendaten an. Ansonsten wird ausgeben, das kein:e Patient:in gefunden wurde.
                if (patient != null) {
                    JOptionPane.showMessageDialog(null, "Gefundener Person.Patient: " + patient);
                } else {
                    JOptionPane.showMessageDialog(null, "Kein Person.Patient mit dieser ID gefunden!");
                }
            } catch (NumberFormatException e) {
                // Zeigt eine Fehlermeldung an, wenn die eingegebene ID keine gültige Zahl ist
                JOptionPane.showMessageDialog(null, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
            }
        } else {
            // Zeigt eine Fehlermeldung an, wenn keine Eingabe erfolgt ist
            JOptionPane.showMessageDialog(null, "Eingabe darf nicht leer sein.");
        }
    }

    /**
     * Diese Methode wird momentan nicht verwendet.
     * Sie dient nur als Platzhalter für zukünftige Implementierungen oder Erweiterungen.
     * @param hauptGUI
     */
    public void execute(HauptGUI hauptGUI) {
        // Zurzeit keine Implementierung.
    }
}