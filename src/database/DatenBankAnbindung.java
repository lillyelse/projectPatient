package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die Klasse "DatenBankAnbindung" stellt eine Verbindung zu einer MySQL-Datenbank her.
 * Sie enthält Methoden zum Öffnen, Testen und Schließen der Verbindung.
 */
public class DatenBankAnbindung {

    private static final String URL = "jdbc:mysql://10.25.2.145:3306/23imrich";
    private static final String USER = "23imrich";
    private static final String PASSWORD = "geb23";

    private static DatenBankAnbindung instanz;
    private static Connection con;


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
            /* 1. MySQL-Treiber laden
            Class.forName() ist eine Möglichkeit dafür: Klassen können so anhand des Klassennamens geladen werden können.*/
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //System.err.println: kennzeichnet die Ausgabe als Fehlermeldung durch (meist) rote Schriftfarbe
            System.err.println("JDBC-Treiber konnte nicht geladen werden: " + e.getMessage());
        }
        try {
            // 2. Verbindung aufbauen
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
        // schon vor dem synchronisierten Block prüfen, ob die Instanz null ist, weil Synchronisierung teuer ist. Wenn die Instanz bereits existiert, kann die Methode ohne Synchronisierung ausgeführt werden.
        if (instanz == null) {
            // syonchronized() nötig, weil ohne Synchronisierung könnten mehrere Threads gleichzeitig feststellen, dass instanz null ist, und mehrere Instanzen der Klasse erstellen. Dies würde das Singleton-Pattern brechen.
            synchronized (DatenBankAnbindung.class) {
                // wieder prüfen, ob die Instanz immer noch null ist
                if (instanz == null) {
                    instanz = new DatenBankAnbindung();
                }
            }
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