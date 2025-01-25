package models;

public class Patient {

    private int patientid;
    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private String strasse;
    private String plz;
    private String ort;
    private String bundesland;
    private int geschlechtID; // 1 = männlich, 2 = weiblich, 3 = divers
    private String krankenkasse;
    private int angehoerigeID;

    public Patient(int patientid, String vorname, String nachname, String geburtsdatum,
                   String strasse, String plz,
                   String ort, String bundesland, int geschlechtID,
                   String krankenkasse, int angehoerigeID )
    {
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
        this.angehoerigeID = angehoerigeID;
    }

    // Getter und Setter
    public int getpatientid() { return patientid; }
    public void setpatientid(int id) { this.patientid = patientid; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }

    public String getGeburtsdatum() { return geburtsdatum; }
    public void setGeburtsdatum(String geburtsdatum) { this.geburtsdatum = geburtsdatum; }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public int getGeschlechtID() {
        return geschlechtID;
    }

    public void setGeschlechtID(int geschlechtID) {
        this.geschlechtID = geschlechtID;
    }

    public String getKrankenkasse() {
        return krankenkasse;
    }

    public void setKrankenkasse(String krankenkasse) {
        this.krankenkasse = krankenkasse;
    }

    public int getAngehoerigeID() {
        return angehoerigeID;
    }

    public void setAngehoerigeID(int angehoerigeID) {
        this.angehoerigeID = angehoerigeID;
    }

    @Override
    public String toString() {
        return "GUI.GUI.models.Patient{" +
                "patientid=" + patientid +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum='" + geburtsdatum + '\'' +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", bundesland='" + bundesland + '\'' +
                '}';
    }

}
