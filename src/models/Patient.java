package models;

import java.util.Date;

/**
 * Die Klasse Patient repräsentiert eine:n Patient:in mit grundlegenden Informationen wie Name, Geburtsdatum, Adresse, Krankenkasse u.a.
 *
 */
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

    /**
     * Konstruktor zur Initialisierung eine:r neuen Patient:in.
     * @param patientid Eindeutige ID des:der Patient:in in der Praxis
     * @param vorname Vorname des:der Patient:in
     * @param nachname Nachname des:der Patient:in
     * @param geburtsdatum Geburtsdatum des:der Patient:in
     * @param strasse Strasse der Adresse des:der Patient:in
     * @param plz Postleitzahl der Adresse des:der Patient:in
     * @param ort Wohnort des:der Patient:in
     * @param bundesland Bundesland der Adresse des:der Patient:in
     * @param geschlechtID ID, die für das Geschlecht des:der Patient:in steht
     * @param krankenkasse Krankenkasse des:der Patient:in
     * @param angehoerigerID ID, die für eine:n Angehörige:n steht
     */
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


    // Getter und Setter: Diese Methoden ermöglichen den Zugriff und die Bearbeitung der Patientendaten.

    /**
     *
     * Diese Methode gibt den aktuellen Wert der privaten Variable patientid zurück.
     * @return aktuellen Wert der Variable patientid
     */
    public int getPatientid() {
        return patientid;
    }

    /**
     * Diese Methode weißt der Variable patientid einen neuen Wert zu.
     * Der Parameter patientid enthält den neuen Wert.
     */
    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }


    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable vorname zurück.
     * @return aktueller Wert der Variable vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Diese Methode weißt der privaten Variable vorname einen neuen Wert zu.
     * Der Parameter vom Typ String enthält den neuen Wert
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable nachname zurück.
     * @return aktueller Wert der Variable nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     *  Diese Methode weißt der privaten Variable nachname einen neuen Wert zu.
     * @param nachname Der Parameter vom Typ String enthält den neuen Wert
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable geburtsdatum zurück.
     * @return aktueller Wert der Variable geburtsdatum
     */
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Diese Methode weißt der privaten Variable nachname einen neuen Wert zu.
     * @param geburtsdatum Der Parameter vom Typ Date enthält den neuen Wert
     */
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable strasse zurück.
     * @return aktueller Wert der Variable strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Diese Methode weißt der privaten Variable strasse einen neuen Wert zu.
     * @param strasse Der Parameter vom Typ String enthält den neuen Wert
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable plz zurück.
     * @return aktueller Wert der Variable plz
     */
    public String getPlz() {
        return plz;
    }

    /**
     * Diese Methode weißt der privaten Variable setPlz einen neuen Wert zu.
     * @param plz Der Parameter vom Typ String enthält den neuen Wert für die Variable plz
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable ort zurück.
     * @return aktueller Wert der Variable ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Diese Methode weißt der privaten Variable ort einen neuen Wert zu.
     * @param ort Der Parameter vom Typ String enthält den neuen Wert für die Variable ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable bundesland zurück.
     * @return aktueller Wert der Variable bundesland
     */
    public String getBundesland() {
        return bundesland;
    }

    /**
     *  Diese Methode weißt der privaten Variable bundesland einen neuen Wert zu.
     * @param bundesland Der Parameter vom Typ String enthält den neuen Wert für die Variable bundesland
     */
    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable geschlecht zurück.
     * @return aktueller Wert der Variable geschlecht
     */
    public int getGeschlechtID() {
        return geschlechtID;
    }

    /**
     * Diese Methode weißt der privaten Variable geschlecht einen neuen Wert zu
     * @param geschlechtID Der Parameter vom Typ int enthält den neuen Wert für die Variable geschlecht
     */
    public void setGeschlechtID(int geschlechtID) {
        this.geschlechtID = geschlechtID;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable krankenkasse zurück
     * @return aktueller Wert der Variable krankenkasse
     */
    public String getKrankenkasse() {
        return krankenkasse;
    }

    /**
     * Diese Methode weißt der privaten Variable krankenkasse einen neuen Wert zu
     * @param krankenkasse Der Parameter vom Typ String enthält den neuen Wert für die Variable krankenkasse
     */
    public void setKrankenkasse(String krankenkasse) {
        this.krankenkasse = krankenkasse;
    }

    /**
     * Diese Methode gibt den aktuellen Wert der privaten Variable angehoeriger zurück
     * @return aktueller Wert der Variable angehoeriger
     */
    public int getAngehoerigerID() {
        return angehoerigerID;
    }

    /**
     * Diese Methode weißt der privaten Variable angehoeriger einen neuen Wert zu
     * @param angehoerigerID Der Parameter vom Typ int enthält den neuen Wert für die Variable angehoeriger
     */
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

