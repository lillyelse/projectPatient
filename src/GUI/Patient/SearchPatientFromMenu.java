package GUI.Patient;// GUI.Patient.SearchPatientFromMenu.java
import GUI.HauptGUI;
import GUI.Patientendatenbank;
import models.Patient;
import javax.swing.*;

public class SearchPatientFromMenu {

    private Patientendatenbank patientenDatenbank;

    public SearchPatientFromMenu(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;
    }


    //METHODEN

    public void searchPatient() {
        String patientIdStr = JOptionPane.showInputDialog(null, "Geben Sie die PatientID ein:");
        if (patientIdStr != null && !patientIdStr.isEmpty()) {
            try {
                int patientId = Integer.parseInt(patientIdStr);
                Patient patient = patientenDatenbank.getPatientById(patientId);
                if (patient != null) {
                    JOptionPane.showMessageDialog(null, "Gefundener Person.Patient: " + patient);
                } else {
                    JOptionPane.showMessageDialog(null, "Kein Person.Patient mit dieser ID gefunden!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Eingabe darf nicht leer sein.");
        }
    }

    public void execute(HauptGUI hauptGUI) {
    }
}