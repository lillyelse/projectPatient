package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatenBankAnbindung {

    private static final String URL = "jdbc:mysql://10.25.2.145:3306/23imrich";
    private static final String USER = "23imrich";
    private static final String PASSWORD = "geb23";

    private static DatenBankAnbindung instanz;
    private static Connection con;

    // Privater Konstruktor, um Instanziierung außerhalb der Klasse zu verhindern
    private DatenBankAnbindung() {
    }

    // Methode, die eine Verbindung zur Datenbank aufbaut (wird von außen verwendet)
    public Connection coni() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL-Treiber laden
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-Treiber konnte nicht geladen werden: " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Datenbankverbindung erfolgreich");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Datenbankverbindung fehlgeschlagen");
        }
        return con;
    }

    // Singleton-Muster, um sicherzustellen, dass nur eine Instanz der Klasse existiert
    public static DatenBankAnbindung getInstanz() {
        if (instanz == null) {
            instanz = new DatenBankAnbindung();
        }
        return instanz;
    }

    // Statische Methode, um eine Verbindung zur Datenbank zu erhalten
    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }

    // Testet die Datenbankverbindung und gibt den Status aus
    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Datenbankverbindung erfolgreich!");
            } else {
                System.out.println("Datenbankverbindung fehlgeschlagen.");
            }
        } catch (SQLException e) {
            System.err.println("Fehler bei der Verbindung: " + e.getMessage());
        }
    }

    // Methode zum Schließen der Verbindung
    public static void close() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Datenbankverbindung geschlossen.");
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung: " + e.getMessage());
            }
        }
    }
}