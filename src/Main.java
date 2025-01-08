import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String URL="jdbc:mysql://localhost:3306/kinderarztpraxis";
    private static final String USER="root";
    private static final String PASSWORD="DatenForever2";

    public static void main(String[] args) {
        coni();
    }

    public static Connection coni(){
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