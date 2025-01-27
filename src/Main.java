
import GUI.Login;
import database.DatenBankAnbindung;

/**
 * Die Main-Klasse ist der Einstiegspunkt der Anwendung.
 */
public class Main {

    /**
     * Der Einstiegspunkt der Anwendung.
     * @param args Die Kommandozeilen-Argumente, die der Anwendung übergeben werden können.
     */
    public static void main(String[] args) {

        DatenBankAnbindung.testConnection();
        /**
         * Initialisiert die Login-GUI.
         */
        new Login();
    }


}

