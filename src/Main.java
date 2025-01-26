
import GUI.Login;

/**
 * Die Main-Klasse ist der Einstiegspunkt der Anwendung.
 */
public class Main {

    /**
     * Der Einstiegspunkt der Anwendung.
     * @param args Die Kommandozeilen-Argumente, die der Anwendung übergeben werden können.
     */
    public static void main(String[] args) {


        //Singleton Pattern,weil nur eine Instanz existieren darf- Der Konstruktor von Login ist privat
        /**
         * Initialisiert die Login-GUI. Es handelt sich um ein Singleton-Pattern,
         * bei dem nur eine Instanz von Login existieren darf.
         */
        new Login();


            }
        }

