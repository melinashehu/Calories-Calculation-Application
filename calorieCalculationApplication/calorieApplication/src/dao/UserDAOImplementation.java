package dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import entity.*;
import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImplementation implements UserDAO {

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
                        rs.getString("role")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

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
                        rs.getString("role")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

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

    @Override
    public boolean updateUser(User user){ //tested and it works
        String sql="UPDATE users SET user_name=?, email=?, password=?, role=? WHERE user_id=?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserEmail());
            pst.setString(3, user.getUserPassword());
            pst.setString(4, user.getRole());
            pst.setInt(5, user.getUserId());
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id){ //tabela foods ka lidhje ON DELETE CASCADE me user id => fshihet dhe tabela foods perkatese
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
}
