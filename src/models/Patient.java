package models;

import java.util.Date;

public class Patient {

    private int patientid;
    private String vorname;
    private String nachname;
    private Date geburtsdatum;
    private String strasse;
    private String plz;
    private String ort;
    private String bundesland;
    private int geschlechtID;
    private String krankenkasse;
    private int angehoerigerID;

    public Patient(int patientid, String vorname, String nachname, java.sql.Date geburtsdatum,
                   String strasse, String plz, String ort, String bundesland,
                   int geschlechtID, String krankenkasse, int angehoerigerID) {

        this.patientid = patientid;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.bundesland = bundesland;
        this.geschlechtID = geschlechtID;
        this.krankenkasse = krankenkasse;
        this.angehoerigerID = angehoerigerID;

    }


    // Getter und Setter für Patientid
    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    // Getter und Setter für Vorname
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    // Getter und Setter für Nachname
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    // Getter und Setter für Geburtsdatum
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    // Getter und Setter für Straße
    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    // Getter und Setter für PLZ
    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    // Getter und Setter für Ort
    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    // Getter und Setter für Bundesland
    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    // Getter und Setter für GeschlechtID
    public int getGeschlechtID() {
        return geschlechtID;
    }

    public void setGeschlechtID(int geschlechtID) {
        this.geschlechtID = geschlechtID;
    }

    // Getter und Setter für Krankenkasse
    public String getKrankenkasse() {
        return krankenkasse;
    }

    public void setKrankenkasse(String krankenkasse) {
        this.krankenkasse = krankenkasse;
    }

    // Getter und Setter für AngehoerigerID
    public int getAngehoerigerID() {
        return angehoerigerID;
    }

    public void setAngehoerigerID(int angehoerigerID) {
        this.angehoerigerID = angehoerigerID;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientid=" + patientid +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", bundesland='" + bundesland + '\'' +
                ", geschlechtID=" + geschlechtID +
                ", krankenkasse='" + krankenkasse + '\'' +
                ", angehoerigerID=" + angehoerigerID +
                '}';
    }





}

