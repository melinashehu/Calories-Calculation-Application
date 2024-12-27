package dao;

import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import calendar.Calendar;
import login.UserSession;


public  class FoodDAOImplementation implements FoodDAO {

    public List<Food> getAllFoods(){
        List<Food> allFoods = new ArrayList<Food>();
        String sql = "select * from user_foods";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Food food = new Food(rs.getInt("user_food_id"), rs.getInt("user_id"),
                        rs.getString("food_name"), rs.getDouble("calorie_value"),
                        rs.getDouble("food_price"), rs.getDate("date_consumed"));
                allFoods.add(food);
            }else{
                System.out.println("No foods found");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return allFoods;
    }

    @Override
    public Food getFood(int id) {
        String sql = "SELECT * FROM user_foods WHERE user_food_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Food(
                        rs.getInt("user_food_id"),
                        rs.getInt("user_id"),
                        rs.getString("food_name"),
                        rs.getDouble("calorie_value"),
                        rs.getDouble("food_price"),
                        rs.getDate("date_consumed")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Food> getAllFoodsFromAWeeklyPeriodForAUser(int userId, java.sql.Date startingDate) {
        Date endDate = Calendar.calculateEndWeekDate(startingDate);
        String sql = "SELECT * FROM user_foods WHERE user_id = ? AND date_consumed BETWEEN ? AND ?";
        List<Food> foods = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setDate(2, startingDate);
            pst.setDate(3, endDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                foods.add(new Food(
                        rs.getInt("user_food_id"),
                        rs.getInt("user_id"),
                        rs.getString("food_name"),
                        rs.getDouble("calorie_value"),
                        rs.getDouble("food_price"),
                        rs.getDate("date_consumed")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return foods;
    }

    @Override
    public List<Food> getAllFoodsForAUser(int userId) {
        String sql = "SELECT * FROM user_foods WHERE user_id = ?";
        List<Food> foods = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                foods.add(new Food(
                        rs.getInt("user_food_id"),
                        rs.getInt("user_id"),
                        rs.getString("food_name"),
                        rs.getDouble("calorie_value"),
                        rs.getDouble("food_price"),
                        rs.getDate("date_consumed")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching foods for user: " + e.getMessage());
        }
        return foods;
    }

//nuk eshte e implentuar sakte pasi e kam shtuar ne menyre qe ta bej klasen jo abstrakte
    @Override
    public List<Food> getAllFoodsForAWeeklyPeriod(java.util.Date startingDate) {
        return null;
    }

    @Override
    public boolean addFood(Food food) {//testuar, punon
        User loggedInUser = UserSession.getLoggedInUser();
        if(loggedInUser != null){
            java.sql.Date currentDate = Calendar.getCurrentDate();
            String sql = "INSERT INTO user_foods (food_name, calorie_value, food_price, date_consumed, user_id) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, food.getFoodName());
                pstmt.setDouble(2, food.getCalorie());
                pstmt.setDouble(3, food.getPrice());
                pstmt.setDate(4, currentDate);
                pstmt.setInt(5, loggedInUser.getUserId());
                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Error adding food: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteFood(int id) {
        String sql = "DELETE FROM user_foods WHERE user_food_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting food: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateFood(Food food) {
        String sql="UPDATE user_foods SET food_name=?, calorie_value=?, food_price=?, date_consumed=? WHERE user_food_id=?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, food.getFoodName());
            pst.setDouble(2, food.getCalorie());
            pst.setDouble(3, food.getPrice());
            pst.setDate(4, (Date) food.getDateWhenConsumed());
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
