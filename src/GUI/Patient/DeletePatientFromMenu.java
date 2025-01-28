// Diese Klasse wird nicht verwendet. Wir lassen sie trotzdem mal stehen.

package GUI.Patient;

import GUI.Patientendatenbank;
import javax.swing.*;

/**
 * Die Klasse DeletePatientFromMenu stellt eine Benutzeroberfläche zur Verfügung,
 * um eine:n Patient:in aus der Datenbank zu löschen.
 * Sie ermöglicht es, die PatientID einzugeben und den:die entsprechende:n Patient:in zu löschen.
 */
public class DeletePatientFromMenu {

    // Attribut
    private Patientendatenbank patientenDatenbank;

    /**
     * Konstruktor der Klasse DeletePatientFromMenu
     * @param patientenDatenbank
     */
    public DeletePatientFromMenu(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;
    }


    /**
     * Diese Methode führt den Löschvorgang für eine:n Patient:in durch.
     * Sie zeigt ein Eingabefenster an, um die PatientID einzugeben.
     * Wenn die PatientID gültig ist, wird der:die Patient:in aus der Datenbank gelöscht und
     * eine Erfolgsmeldung wird angezeigt. Andernfalls wird eine Fehlermeldung angezeigt.
     * @param parent Das übergeordnete JFrame
     */
    public void execute(JFrame parent) {
        // Zeigt ein Dialogfenster an, in dem die PatientID eingegeben werden kann.
        String patientIdStr = JOptionPane.showInputDialog(parent, "Geben Sie die PatientID ein:");

        if (patientIdStr != null) {
            try {
                // Versucht, die eingegebene PatientID in eine int- Zahl umzuwandeln
                int patientId = Integer.parseInt(patientIdStr);
                // Löscht den:die Patient:in, wenn die ID gültig ist
                if (patientenDatenbank.deletePatient(patientId)) {
                    JOptionPane.showMessageDialog(parent, "Person.Patient erfolgreich gelöscht!");
                }
            } catch (Exception e) {
                // Zeigt eine Fehlermeldung an, wenn die Eingabe ungültig ist
                JOptionPane.showMessageDialog(parent, "Fehler: " + e.getMessage());
            }
        }
    }
}
