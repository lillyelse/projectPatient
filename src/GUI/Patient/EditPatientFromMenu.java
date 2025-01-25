package GUI.Patient;

import GUI.Patientendatenbank;
import models.Patient;
import javax.swing.*;

public class EditPatientFromMenu {

    //ATTRIBUTE
    private Patientendatenbank patientenDatenbank;

    //KONSTRUKTOR
    public EditPatientFromMenu(Patientendatenbank patientenDatenbank) {
        this.patientenDatenbank = patientenDatenbank;
    }

    //METHODEN

    public void execute(JFrame parent) {
        String patientIdStr = JOptionPane.showInputDialog(parent, "Geben Sie die PatientID ein:");
        if (patientIdStr != null) {
            try {
                int patientId = Integer.parseInt(patientIdStr);
                Patient patient = patientenDatenbank.getPatientById(patientId);
                if (patient != null) {
                    JTextField vornameField = new JTextField(patient.getVorname());
                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Vorname:"));
                    panel.add(vornameField);
                    int result = JOptionPane.showConfirmDialog(parent, panel, "Person.Patient bearbeiten", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        patient.setVorname(vornameField.getText());
                        if (patientenDatenbank.updatePatient(patient)) {
                            JOptionPane.showMessageDialog(parent, "Person.Patient erfolgreich bearbeitet!");
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Fehler: " + e.getMessage());
            }
        }
    }
}
