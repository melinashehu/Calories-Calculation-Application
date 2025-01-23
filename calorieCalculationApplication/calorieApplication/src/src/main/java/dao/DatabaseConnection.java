package dao;

/**
 * Handles the connection to the application's database.
 * Do NOT modify this class unless there is a need to change the database URL, username or password.
 * Incorrect modifications can break the connection to the database and disrupt the entire application.
 */

import javafx.beans.value.ObservableBooleanValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author :Amina
 */
public class DatabaseConnection implements DatabaseConnectionInterface{
    private static final String url = "jdbc:mysql://localhost:3306/caloriescalculatorapp";
    private static final String user = "root";
    private static final String password = "";

    @Override
    public  Connection getConnection() throws SQLException {
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
