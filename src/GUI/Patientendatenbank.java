package GUI;

import database.DatenBankAnbindung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//für Queries:

    class Patientendatenbank {

        private DatenBankAnbindung db;

        public Patientendatenbank() {
            db = DatenBankAnbindung.getInstanz();
            db.coni();
        }

        public Patient getPatientById(int id) {

            String query = "SELECT * FROM patient WHERE PatientID = ?";
            try (PreparedStatement pstmt = db.coni().prepareStatement(query)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("PatientID"),
                            rs.getString("Vorname"),
                            rs.getString("Nachname"),
                            rs.getString("Geburtsdatum"),
                            rs.getString("Strasse"),
                            rs.getString("PLZ"),
                            rs.getString("Ort"),
                            rs.getString("Bundesland"),
                            rs.getInt("GeschlechtID"),
                            rs.getString("Krankenkasse"),
                            rs.getInt("AngehoerigerID")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public List<Patient> getAllPatients() throws SQLException{
            List<Patient> patient = new ArrayList<>();
            // Angepasste Abfrage mit JOINs für geschlecht, krankenkasse, und bundesland
            String query = "SELECT p.PatientID, p.Vorname, p.Nachname, p.Geburtsdatum, p.Strasse, p.PLZ, p.Ort, "
                    + "b.Bezeichnung AS Bundesland, g.Bezeichnung AS Geschlecht, k.Bezeichnung AS Krankenkasse, "
                    + "a.Bezeichnung AS Angehoeriger "
                    + "FROM patient p "
                    + "LEFT JOIN bundesland b ON p.Bundesland = b.BundeslandID "
                    + "LEFT JOIN geschlecht g ON p.GeschlechtID = g.GeschlechtID "
                    + "LEFT JOIN krankenkasse k ON p.Krankenkasse = k.KrankenkasseID "
                    + "LEFT JOIN angehoeriger a ON p.AngehoerigerID = a.AngehoerigerID";

            try (Connection connection = db.coni(); // Verbindung abrufen
                 Statement statement = connection.createStatement(); // Statement erstellen
                 ResultSet rs = statement.executeQuery(query)) { // Query ausführen

                while (rs.next()) {
                    patient.add(new Patient(
                            rs.getInt("PatientID"), // ID des Patienten
                            rs.getString("Vorname"),
                            rs.getString("Nachname"),
                            rs.getString("Geburtsdatum"),
                            rs.getString("Strasse"),
                            rs.getString("PLZ"),
                            rs.getString("Ort"),
                            rs.getString("Bundesland"), // Bundesland aus der Tabelle
                            rs.getInt("Geschlecht"), // Geschlecht aus der Tabelle
                            rs.getString("Krankenkasse"), // Krankenkasse aus der Tabelle
                            rs.getInt("Angehoeriger") // Angehöriger aus der Tabelle
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return patient;
        }


        public boolean addPatient(Patient patient) {
            String query = "INSERT INTO patient (Vorname, Nachname, Geburtsdatum, Strasse, PLZ, Ort, Bundesland, GeschlechtID, Krankenkasse, AngehoerigerID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = db.coni().prepareStatement(query)) {
                pstmt.setString(1, patient.getVorname());
                pstmt.setString(2, patient.getNachname());
                pstmt.setString(3, patient.getGeburtsdatum());
                pstmt.setString(4, patient.getStrasse());
                pstmt.setString(5, patient.getPlz());
                pstmt.setString(6, patient.getOrt());
                pstmt.setString(7, patient.getBundesland());
                pstmt.setInt(8, patient.getGeschlechtID());
                pstmt.setString(9, patient.getKrankenkasse());
                pstmt.setInt(10, patient.getAngehoerigeID());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }


        public boolean deletePatient(int id) {
            String query = "DELETE FROM patient WHERE PatientID = ?";
            try (PreparedStatement pstmt = db.coni().prepareStatement(query)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public void close() {
            db.close();
        }



}
