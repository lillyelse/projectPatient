package GUI;

import database.DatenBankAnbindung;
import models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//für Queries:

    public class Patientendatenbank {

        private DatenBankAnbindung db;

        public Patientendatenbank() {
            db = DatenBankAnbindung.getInstanz();
            db.coni();
        }

        public Patient getPatientById(int id) {

            String query = "SELECT * FROM patient WHERE PatientID = ?";
            try (Connection connection = db.coni();
                 PreparedStatement pstmt = connection.prepareStatement(query))  {
                pstmt.setInt(1, id);
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
                }else {
                    System.out.println("Kein Person.Patient mit der ID " + id + " gefunden.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public List<Patient> getAllPatients() {
            List<Patient> patients = new ArrayList<>();
            String query = "SELECT * FROM patient";
            try (Connection connection = db.coni();
                 Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(query)) {

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


        public boolean addPatient(Patient patient) {
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

                return pstmt.executeUpdate() > 0;
            }
            catch (SQLException e) {
                // Detaillierte Fehlerbehandlung: Gib die Fehlermeldung aus und mache ggf. eine Log-Ausgabe
                System.err.println("Datenbankfehler: " + e.getMessage());
                e.printStackTrace();
                return false; // Wenn ein Fehler auftritt, gebe false zurück
            }

        }


        public boolean deletePatient(int patientId) throws SQLException {

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

            }
            catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }

        public void close() {
            db.close();
        }



}
