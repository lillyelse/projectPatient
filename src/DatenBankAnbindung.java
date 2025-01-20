import java.sql.*;

public class DatenBankAnbindung {

    private static DatenBankAnbindung instanz;

    private static final String URL="jdbc:mysql://10.25.2.145:3306/23imrich";
    private static final String USER="23imrich";
    private static final String PASSWORD="geb23";

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
