package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticalReportDAOImplementation implements StatisticalReportDAO {
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

    public double calculateMonthlySpendingForAUser(int userId, java.sql.Date startingDate){
        String sql = "SELECT SUM(food_price) AS totalSpending " +
                "FROM user_foods " +
                "WHERE user_id = ? " +
                "AND date_consumed BETWEEN ? AND DATE_ADD(?, INTERVAL 1 MONTH)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDate(2, startingDate);
            pstmt.setDate(3, startingDate);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("totalSpending");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating monthly spending for user: " + e.getMessage());
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


