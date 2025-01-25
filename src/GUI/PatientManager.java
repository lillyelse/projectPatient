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



}
