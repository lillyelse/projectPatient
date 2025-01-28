package GUI;

import database.DatenBankAnbindung;
import models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//für Queries:

/**
 * Diese Klasse stellt Methoden für den Zugriff und die Verwaltung von Patientendaten in der Datenbank bereit.
 * Sie ermöglicht CRUD-Operationen.
 */
public class Patientendatenbank {

    // ATTRIBUT
    private DatenBankAnbindung db;

    /**
     * Konstruktor für die Patientendatenbank.
     * Initialisiert die Verbindung zur Datenbank.
     */
    public Patientendatenbank() {
            db = DatenBankAnbindung.getInstanz();
            db.coni();
        }
            // METHODEN

            /**
             * Diese Methode ruft die Informationen eine:r Patient:in anhand der ID aus der Datenbank ab.
             * @param id Die ID des:der Patient:in, der:die abgerufen werden soll.
             * @return Ein Patient-Objekt oder null, wenn kein:e Patient:in gefunden wurde.
             */
            public Patient getPatientById ( int id){
                String query = "SELECT * FROM patient WHERE PatientID = ?";
                try (Connection connection = db.coni();
                     // SQL-Anweisung wird vorkompiliert und in ein Objekt gespeichert
                     PreparedStatement pstmt = connection.prepareStatement(query)) {
                    if (connection == null) {
                        System.out.println("Fehler: Keine gültige Verbindung zur Datenbank.");
                        return null;
                    }
                    System.out.println("Abfrage gestartet mit ID: " + id);
                    pstmt.setInt(1, id);
                    // PreparedStatement liefert ein ResultSet-Objekt zurück
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        return new Patient(
                                rs.getInt("PatientID"),
                                rs.getString("Vorname"),
                                rs.getString("Nachname"),
                                rs.getDate("Geburtsdatum"),
                                rs.getString("Strasse"),
                                rs.getString("PLZ"),
                                rs.getString("Ort"),
                                rs.getString("Bundesland"),
                                rs.getInt("GeschlechtID"),
                                rs.getString("Krankenkasse"),
                                rs.getInt("AngehoerigerID")
                        );
                    } else {
                        System.out.println("Kein Patient mit der ID " + id + " gefunden."); // Ausgabe falls kein Patient gefunden wurde
                    }
                } catch (SQLException e) {
                    System.out.println("Fehler bei der SQL-Abfrage: " + e.getMessage());  // Fehlerausgabe bei SQL-Problemen
                    e.printStackTrace();
                }
                return null;
            }


            /**
             * Diese Methode ruft alle Patient:innen aus der Datenbank ab.
             * @return Eine Liste von Patient-Objekten, die alle Patient:innen aus der Datenbank repräsentieren.
             */
            public List<Patient> getAllPatients() {
                //Liste erstellen
                List<Patient> patients = new ArrayList<>();
                String query = "SELECT * FROM patient";
                try (Connection connection = db.coni();
                     // Statement wird erstellt
                     Statement statement = connection.createStatement();
                     // Statement liefert ein ResultSet zurück
                     ResultSet rs = statement.executeQuery(query)) {

                    // bewegt den Zeiger des ResultSet-Objekts auf die nächste Zeile in der Ergebnisse, solange es eine nächste Zeile findet
                    while (rs.next()) {
                        patients.add(new Patient(
                                rs.getInt("PatientID"),
                                rs.getString("Vorname"),
                                rs.getString("Nachname"),
                                rs.getDate("Geburtsdatum"),
                                rs.getString("Strasse"),
                                rs.getString("PLZ"),
                                rs.getString("Ort"),
                                rs.getString("Bundesland"),
                                rs.getInt("GeschlechtID"),
                                rs.getString("Krankenkasse"),
                                rs.getInt("AngehoerigerID")
                        ));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return patients;
            }


            /**
             * Fügt eine:n neue:n Patient:in zur Datenbank hinzu.
             * @param patient Das Patient-Objekt, das hinzugefügt werden soll.
             * @return true, wenn der:die Patient:in erfolgreich hinzugefügt wurde, andernfalls false.
             */
            public boolean addPatient(Patient patient){
                String query = "INSERT INTO patient (Vorname, Nachname, Geburtsdatum, Strasse, PLZ, Ort, Bundesland, GeschlechtID, Krankenkasse, AngehoerigerID) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = db.coni().prepareStatement(query)) {
                    pstmt.setString(1, patient.getVorname());
                    pstmt.setString(2, patient.getNachname());
                    if (patient.getGeburtsdatum() != null) {
                        pstmt.setDate(3, new java.sql.Date(patient.getGeburtsdatum().getTime()));
                    } else {
                        pstmt.setNull(3, java.sql.Types.DATE); // Falls Geburtsdatum null ist, setze es auf NULL in der DB
                    }
                    pstmt.setString(4, patient.getStrasse());
                    pstmt.setString(5, patient.getPlz());
                    pstmt.setString(6, patient.getOrt());
                    pstmt.setString(7, patient.getBundesland());
                    pstmt.setInt(8, patient.getGeschlechtID());
                    pstmt.setString(9, patient.getKrankenkasse());
                    pstmt.setInt(10, patient.getAngehoerigerID());

                    // überprüfen, ob ein SQL-Update erfolgreich war:
                    // gibt einen int-Wert zurück: dieser entspricht der Anzahl der betroffenen Zeilen in der Datenbank.
                    // wenn der Wert > 0: true: Mindestens eine Zeile wurde betroffen - ansonsten false: Keine Zeilen wurden betroffen
                    return pstmt.executeUpdate() > 0;
                } catch (SQLException e) {
                    // Detaillierte Fehlerbehandlung: Gib die Fehlermeldung aus und mache ggf. eine Log-Ausgabe
                    System.err.println("Datenbankfehler: " + e.getMessage());
                    e.printStackTrace();
                    return false; // Wenn ein Fehler auftritt, gebe false zurück
                }

            }


            /**
             * Löscht eine:n Patient:in aus der Datenbank anhand der ID.
             * @param patientId Die ID des:der Patient:in, der:die gelöscht werden soll.
             * @return true, wenn Patient:in erfolgreich gelöscht - ansonsten false.
             * @throws SQLException Wenn ein Fehler bei der Datenbankoperation auftritt
             */
            public boolean deletePatient ( int patientId) throws SQLException {

                String updateBefundQuery = "UPDATE befund SET PatientID = NULL WHERE PatientID = ?";


                String deletePatientQuery = "DELETE FROM patient WHERE PatientID = ?";

                try (Connection connection = db.coni();
                     PreparedStatement updateStmt = connection.prepareStatement(updateBefundQuery);
                     PreparedStatement deleteStmt = connection.prepareStatement(deletePatientQuery)) {

                    // Setze die PatientID auf NULL in der Befund-Tabelle
                    updateStmt.setInt(1, patientId);
                    int befundeUpdated = updateStmt.executeUpdate();

                    // Lösche den Patienten aus der Person.Patient-Tabelle
                    deleteStmt.setInt(1, patientId);
                    int patientDeleted = deleteStmt.executeUpdate();

                    // Wenn sowohl die Befunde geändert als auch der Person.Patient gelöscht wurde, dann true zurückgeben
                    return befundeUpdated >= 0 && patientDeleted > 0;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }

            }


            /**
             * Aktualisiert die Daten eine:r bestehenden Patient:in in der Datenbank.
             * @param patient Das Patient-Objekt mit den aktualisierten Daten.
             * @return true, wenn die Daten erfolgreich aktualisiert wurden, ansonsten false.
             */
            public boolean updatePatient (Patient patient){
                String query = "UPDATE patient SET Vorname = ?, Nachname = ?, Geburtsdatum = ?, Strasse = ?, PLZ = ?, Ort = ?, Bundesland = ?, GeschlechtID = ?, Krankenkasse = ?, AngehoerigerID = ? WHERE PatientID = ?";
                try (Connection connection = db.coni();
                     PreparedStatement pstmt = connection.prepareStatement(query)) {

                    pstmt.setString(1, patient.getVorname());
                    pstmt.setString(2, patient.getNachname());
                    pstmt.setDate(3, (Date) patient.getGeburtsdatum());
                    pstmt.setString(4, patient.getStrasse());
                    pstmt.setString(5, patient.getPlz());
                    pstmt.setString(6, patient.getOrt());
                    pstmt.setString(7, patient.getBundesland());
                    pstmt.setInt(8, patient.getGeschlechtID());
                    pstmt.setString(9, patient.getKrankenkasse());
                    pstmt.setInt(10, patient.getAngehoerigerID());
                    pstmt.setInt(11, patient.getPatientid());

                    // überprüfen, ob ein SQL-Update erfolgreich war:
                    // gibt einen int-Wert zurück: dieser entspricht der Anzahl der betroffenen Zeilen in der Datenbank.
                    // wenn der Wert > 0: true: Mindestens eine Zeile wurde betroffen - ansonsten false: Keine Zeilen wurden betroffen
                    return pstmt.executeUpdate() > 0;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
            }


        }


