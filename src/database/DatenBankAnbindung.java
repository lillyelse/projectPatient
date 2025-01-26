package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die Klasse "DatenBankAnbindung" stellt eine Verbindung zu einer MySQL-Datenbank her.
 * Sie enthält Methoden zum Öffnen, Testen und Schließen der Verbidung.
 */
public class DatenBankAnbindung {

    private static final String URL = "jdbc:mysql://10.25.2.145:3306/23imrich";
    private static final String USER = "23imrich";
    private static final String PASSWORD = "geb23";

    private static DatenBankAnbindung instanz;
    private static Connection con;

    // Privater Konstruktor, um Instanziierung außerhalb der Klasse zu verhindern

    /**
     * Privater Konstruktor, um eine Instanziierung außerhalb der Klasse zu verhindern.
     * Somit wird das Singleton-Muster gewährleistet, bei dem nur eine Instanz existieren darf.
     */
    private DatenBankAnbindung() {
    }


    /**
     * Diese Methode baut eine Verbindung zur Datenbank auf.
     * @return Eine Connection-Instanz, die die Verbindung zur MySQL-Datenbank darstellt.
     */
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

    /**
     * Diese Methode gibt die Instanz der DatenBankAnbindung zurück.
     * Wenn die Instanz noch nicht existiert, wird sie erstellt.
     * Das Singleton-Muster stellt sicher, dass immer nur eine Instanz der Klasse existiert.
     * @return die Instanz der DatenBankAnbindung
     */
    public static DatenBankAnbindung getInstanz() {
        if (instanz == null) {
            instanz = new DatenBankAnbindung();
        }
        return instanz;
    }

    /**
     * Diese statische Methode gibt die bestehende Datenbankverbindung zurück.
     * Wenn keine Verbindung besteht oder diese geschlossen ist, wird eine neue Verbindung hergestellt.
     * @return Die Connection-Instanz, die die Verbindung zur Datenbank darstellt.
     * @throws SQLException Falls bei der Verbindung zur Datenbank ein Fehler auftritt.
     */
    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }

    /**
     * Diese statische Methode testet die Verbindung zur Datenbank und gibt das Ergebnis aus.
     */
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

    /**
     * Diese statische Methode schließt die bestehende Datenbankverbindung, falls eine offene Verbindung besteht.
     * Sie gibt eine Erfolgsmeldung bzw. Fehlermeldung aus.
     */
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