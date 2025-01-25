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
                pstmt.setDate(3, patient.getGeburtsdatum());
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
