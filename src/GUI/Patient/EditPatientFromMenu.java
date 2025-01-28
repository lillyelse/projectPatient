// Diese Klasse wird nicht verwendet. Wir lassen sie trotzdem mal stehen.


package GUI.Patient;

import GUI.Patientendatenbank;
import models.Patient;
import javax.swing.*;

/**
 * Die Klasse EditPatientFromMenu stellt eine Benutzeroberfläche zur Verfügung,
 * um die Daten der Patient:innen zu bearbeiten.
 * Der:die Benutzer:in gibt eine PatientID ein und kann dann den Vornamen ändern.
 */
public class EditPatientFromMenu {

    //ATTRIBUTE
    private Patientendatenbank patientenDatenbank;

    //KONSTRUKTOR
    /**
     * Dieser Konstruktor initialisiert die Klasse mit der Patientendatenbank.
     * @param patientenDatenbank Die Patientendatenbank, die für das Bearbeiten der Patientendaten verwendet wird.
     */
    public EditPatientFromMenu(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;
    }

    //METHODEN

    /**
     * Die Methode ermöglicht das "Bearbeiten" der Patient:innnen
     * Zeigt ein Dialogfenster an, in dem die PatientID eingegeben werden kann.
     * Wenn ein:e Patient:in mit dieser ID gefunden wird, wird der Vorname angezeigt und kann bearbeitet werden.
     * Die Änderungen werden in der Datenbank gespeichert.
     * @param parent das übergeordnete JFrame
     */
    public void execute(JFrame parent) {
        // Zeigt ein Dialogfenster an, in dem die PatientID eingegeben werden kann.
        String patientIdStr = JOptionPane.showInputDialog(parent, "Geben Sie die PatientID ein:");

        if (patientIdStr != null) {
            try {
                // Versucht, die eingegebene PatientID in eine integer-Zahl umzuwandeln
                int patientId = Integer.parseInt(patientIdStr);
                // Holt den:die Patient:in anhand der PatientID aus der Datenbank
                Patient patient = patientenDatenbank.getPatientById(patientId);
                // Wenn der:die Patient:in gefunden wurde, zeigt es das Eingabefeld für den Vornamen an
                if (patient != null) {
                    JTextField vornameField = new JTextField(patient.getVorname());
                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Vorname:"));
                    panel.add(vornameField);
                    // Bestätigungsdialog zum Bearbeiten des Vornamens
                    int result = JOptionPane.showConfirmDialog(parent, panel, "Person.Patient bearbeiten", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        // Setzt den neuen Vornamen und aktualisiert den:die Patientin in der Datenbank
                        patient.setVorname(vornameField.getText());
                        if (patientenDatenbank.updatePatient(patient)) {
                            JOptionPane.showMessageDialog(parent, "Person.Patient erfolgreich bearbeitet!");
                        }
                    }
                }
            } catch (Exception e) {
                // Zeigt eine Fehlermeldung an
                JOptionPane.showMessageDialog(parent, "Fehler: " + e.getMessage());
            }
        }
    }
}
