import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

package kinderarztpraxis;


public class DatenBankAnbindung {
    private static final String URL="jdbc:mysql://localhost:3306/LokalerDBServer";
    private static final String USER="root";
    private static final String PASSWORD="DatenForever2";

    public static Connection getConnection(){
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

}
