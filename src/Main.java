public class Main {

    public static void main(String[] args) {

        //Singleton Pattern,weil nur eine Instanz existieren darf-konstruktor privat deswegen rot
        DatenBankAnbindung dbAnbindung = DatenBankAnbindung.getInstanz();
        dbAnbindung.coni(); // Verbindung zur Datenbank wird getestet
            }
        }

