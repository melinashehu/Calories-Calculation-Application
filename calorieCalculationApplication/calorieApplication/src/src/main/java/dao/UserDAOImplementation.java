package dao;

/**
 * Implementation of the UserDAO interface.
 * This class provides methods to interact with the 'users' table in the database.
 * Incorrect changes to the SQL queries or methods in this class can lead to data corruption or loss of functionality.
 */

//import com.mysql.cj.x.protobuf.MysqlxPrepare;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO {

    /**
     * @author :Amina
     */
    public List<User> getAllUsers() { //tested and it works
        List<User> allUsers = new ArrayList<User>();
        String sql = "select * from users WHERE role='USER'";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getBoolean("hasExceededMoneyLimit"));
                allUsers.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return allUsers;
    }

    /**
     * @author :Amina
     */
    @Override
    public User getUserById(int id){ //tested and it works :)
        String sql="SELECT * FROM users WHERE user_id= ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getBoolean("hasExceededMoneyLimit")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> getAllUsersIds(){
        List<Integer> userIds = new ArrayList<>();
        String sql = "SELECT user_id FROM users ORDER BY user_id ASC";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                userIds.add(rs.getInt("user_id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userIds;
    }


    /**
     * @author :Amina
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password){ //tested and it works
        String sql="SELECT * FROM users WHERE email= ? AND password= ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getBoolean("hasExceededMoneyLimit")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @author :Amina
     */
    @Override
    public boolean addUser(User user){ //tested and it works :)
        String sql="INSERT INTO users(user_name, email, password, role) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserEmail());
            pst.setString(3, user.getUserPassword());
            pst.setString(4, user.getRole());
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @author :Amina
     */
    @Override
    public boolean updateUser(User user){ //tested and it works
        String sql="UPDATE users SET user_name=?, email=?, password=?, role=?, hasExceededMoneyLimit =? WHERE user_id=?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserEmail());
            pst.setString(3, user.getUserPassword());
            pst.setString(4, user.getRole());
            pst.setBoolean(5, user.getHasExceededMoneyLimit());
            pst.setInt(6, user.getUserId());
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @author :Amina
     */
    @Override
    public boolean deleteUser(int id){
        String sql="DELETE FROM users WHERE user_id= ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            System.err.println("Error while deleting user");
            return false;
        }
    }

    @Override
    public List<Boolean> getHasExceededMoneyLimitColumn(){
        List<Boolean> exceededMoneyLimitColumn = new ArrayList<>();
        String sql="SELECT hasExceededMoneyLimit FROM users";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                exceededMoneyLimitColumn.add(rs.getBoolean("hasExceededMoneyLimit"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exceededMoneyLimitColumn;
    }
}
