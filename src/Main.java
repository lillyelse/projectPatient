
import GUI.Login;
import database.DatenBankAnbindung;

import javax.swing.*;

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login(); // Create and show the Login frame
            }
        });
    }


}

