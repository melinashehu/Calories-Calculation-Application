package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String url="jdbc:mysql://localhost:3306/caloriescalculatorapp";
    private static final String user="root";
    private static final String password="";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    /*per arsye testimi
    public static void main(String[] args) throws SQLException{
        try(Connection con=getConnection()){
            System.out.println("Lidhja u be me sukses");
        }catch(SQLException e){
            System.out.println("Lidhja nuk u realizua");
            e.printStackTrace();
        }
    }*/
}
