import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatenBankAnbindung {

    private static DatenBankAnbindung instanz;

    private static final String URL="jdbc:mysql://localhost:3306/kinderarztpraxis";
    private static final String USER="root";
    private static final String PASSWORD="DatenForever2";

    // Privater Konstruktor, um Instanziierung außerhalb der Klasse zu verhindern
    private DatenBankAnbindung() {}

    public Connection coni(){
        Connection con=null;
        try{
            con = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conncected to database");
        }catch(
                SQLException e){
            e.printStackTrace();
            System.out.println("Conncection failed");
        }
        return con;
    }

    public static DatenBankAnbindung getInstanz() {

        if (instanz == null) {
            instanz = new DatenBankAnbindung();
        }

        return instanz;
    }

}
