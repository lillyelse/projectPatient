package GUI.Patient;

import GUI.Patientendatenbank;
import models.Patient;     //Models -> Patient geplant

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class AddPatientFromMenu {

    private Patientendatenbank patientenDatenbank;


    public AddPatientFromMenu(Patientendatenbank patientenDatenbank) {
            this.patientenDatenbank = patientenDatenbank;
        }


    public void addPatient() {
            // Panel für die Eingabefelder
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Textfelder-Objekte erstellen
            JTextField vornameField = new JTextField(20);
            JTextField nachnameField = new JTextField(20);
            JTextField geburtsdatumField = new JTextField(20);
            JTextField strasseField = new JTextField(20);
            JTextField plzField = new JTextField(20);
            JTextField ortField = new JTextField(20);
            JTextField bundeslandField = new JTextField(20);
            JTextField geschlechtField = new JTextField(20);
            JTextField krankenkasseField = new JTextField(20);
            JTextField angehoerigerField = new JTextField(20);

            // diese Textfelder und neue Label-Objekte zum Panel hinzufügen
            panel.add(new JLabel("Vorname:"));
            panel.add(vornameField);
            panel.add(new JLabel("Nachname:"));
            panel.add(nachnameField);
            panel.add(new JLabel("Geburtsdatum (yyyy-MM-dd):"));
            panel.add(geburtsdatumField);
            panel.add(new JLabel("Straße:"));
            panel.add(strasseField);
            panel.add(new JLabel("PLZ:"));
            panel.add(plzField);
            panel.add(new JLabel("Ort:"));
            panel.add(ortField);
            panel.add(new JLabel("Bundesland:"));
            panel.add(bundeslandField);
            panel.add(new JLabel("Geschlecht ID:"));
            panel.add(geschlechtField);
            panel.add(new JLabel("Krankenkasse:"));
            panel.add(krankenkasseField);
            panel.add(new JLabel("Angehöriger ID:"));
            panel.add(angehoerigerField);



        }
}


