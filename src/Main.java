
//FEATURES KINDERARZTPRAXIS DR.BERGER

// FUNKTIONIERT zu 100 %: Log-In
// FUNKTIONIERT zu 100 %: Suchfunktion mit REG-EX
// FUNKTIONIERT ZU 90-100 %: Export/Druckfunktion/Abspeichern als PDF am Desktop

import GUI.Login;
import database.DatenBankAnbindung;

import javax.swing.*;

/**
 * Die Main-Klasse ist der Einstiegspunkt der Anwendung.
 */
public class Main {

    /**
     * Der Einstiegspunkt der Anwendung.
     * Ruft die Methode testConnecion() auf und initialisiert die Login-GUI.
     * @param args Die Kommandozeilen-Argumente, die der Anwendung übergeben werden können.
     */
    public static void main(String[] args) {

        DatenBankAnbindung.testConnection();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login(); // Create and show the Login frame
            }
        });
    }


}


