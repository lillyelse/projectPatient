package GUI.Patient;

import GUI.Patientendatenbank;
import javax.swing.*;


public class DeletePatientFromMenu {

    // Attribut
    private Patientendatenbank patientenDatenbank;

    // Konstruktor
    public DeletePatientFromMenu(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;
    }


    // Methode
    public void execute(JFrame parent) {
        String patientIdStr = JOptionPane.showInputDialog(parent, "Geben Sie die PatientID ein:");

        if (patientIdStr != null) {
            try {
                int patientId = Integer.parseInt(patientIdStr);
                if (patientenDatenbank.deletePatient(patientId)) {
                    JOptionPane.showMessageDialog(parent, "Person.Patient erfolgreich gelöscht!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Fehler: " + e.getMessage());
            }
        }
    }
}
