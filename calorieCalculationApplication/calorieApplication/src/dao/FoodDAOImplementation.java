package dao;

import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import calendar.Calendar;


public  class FoodDAOImplementation implements FoodDAO {

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
    public boolean addFood(Food food) {
        String sql = "INSERT INTO user_foods (user_food_id, food_name, calorie_value, food_price, date_consumed) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, food.getFoodId());
            pstmt.setString(2, food.getFoodName());
            pstmt.setDouble(3, food.getCalorie());
            pstmt.setDouble(4, food.getPrice());
            pstmt.setDate(5, new Date(food.getDateWhenConsumed().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding food: " + e.getMessage());
            return false;
        }
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
    public double calculateTotalCaloriesConsumed(int userId) {
        String sql = "SELECT SUM(calorie_value) AS totalCalories FROM user_foods WHERE user_id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("totalCalories");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total calories: " + e.getMessage());
        }
        return 0.0;
    }

    @Override
    public double calculateTotalSpendingMoney(int userId) {
        String sql = "SELECT SUM(food_price) AS totalSpending FROM user_foods WHERE user_id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("totalSpending");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total spending money: " + e.getMessage());
        }
        return 0.0;
    }

    @Override
    public int calculateDaysAboveCalorieThreshold(int userId, double calorieThreshold) {
        String sql = "SELECT COUNT(DISTINCT date_consumed) AS daysAboveThreshold FROM user_foods WHERE user_id = ? GROUP BY date_consumed HAVING SUM(calorie_value) > ?";
        int daysAboveThreshold = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, calorieThreshold);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                daysAboveThreshold++;
            }
        } catch (SQLException e) {
            System.err.println("Error calculating days above calorie threshold: " + e.getMessage());
        }
        return daysAboveThreshold;
    }
}
