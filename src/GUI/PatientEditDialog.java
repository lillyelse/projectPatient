package GUI;

import models.Patient;

import javax.swing.*;

public class PatientEditDialog extends JDialog {

    private JTextField vornameField, nachnameField, geburtsdatumField, strasseField, plzField, ortField,
            bundeslandField, geschlechtField, krankenkasseField, angehoerigerField;
    private Patient patient;
    private Patientendatenbank patientenDatenbank;
    private HauptGUI hauptGUI;

    public PatientEditDialog() {

    }
}
