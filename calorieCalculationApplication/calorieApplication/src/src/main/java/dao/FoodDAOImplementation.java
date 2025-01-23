package dao;

/**
 * Implementation of the FoodDAO interface.
 * This class provides methods to interact with the 'user_foods' table in the database.
 * Incorrect changes to the SQL queries or methods in this class can lead to data corruption or loss of functionality.
 */


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

    private final DatabaseConnection dbConnectionFactory;

    public FoodDAOImplementation() {
        this.dbConnectionFactory = new DatabaseConnection();
    }

    /**
     * @author :Amina
     */
    public List<Food> getAllFoods(){
        List<Food> allFoods = new ArrayList<Food>();
        String sql = "select * from user_foods";
        try(Connection conn = dbConnectionFactory.getConnection();
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

    /**
     * @author :Amina
     */
    @Override
    public Food getFood(int id) {
        String sql = "SELECT * FROM user_foods WHERE user_food_id=?";
        try (Connection conn = dbConnectionFactory.getConnection();
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

    /**
     * @author :Amina
     */
    @Override
    public List<Food> getAllFoodsFromAWeeklyPeriodForAUser(int userId, java.sql.Date startingDate) {
        Date endDate = Calendar.calculateEndWeekDate(startingDate);
        String sql = "SELECT * FROM user_foods WHERE user_id = ? AND date_consumed BETWEEN ? AND ?";
        List<Food> foods = new ArrayList<>();
        try (Connection conn = dbConnectionFactory.getConnection();
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

    /**
     * @author :Amina
     */
    public List<Double> getCalorieValuesForAWeeklyPeriodForAUser(int userId, java.sql.Date startingDate){
        Date endDate = Calendar.calculateEndWeekDate(startingDate);
        String sql = "SELECT calorie_value FROM user_foods WHERE user_id = ? AND date_consumed BETWEEN ? AND ?";
        List<Double> calorieValuesForAWeeklyPeriodForAUser = new ArrayList<>();

        try (Connection connection = dbConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1,userId);
            pstmt.setDate(2, startingDate);
            pstmt.setDate(3,endDate);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                calorieValuesForAWeeklyPeriodForAUser.add(rs.getDouble("calorie_value"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching calorie values for user: " + e.getMessage());
        }
        return calorieValuesForAWeeklyPeriodForAUser;
    }


    /**
     * @author :Edna
     */
    @Override
    public List<Double> getMoneySpentFromAUser(int userId, java.sql.Date startingDate){
        String sql = "SELECT food_price FROM user_foods WHERE user_id = ? AND date_consumed BETWEEN ? AND DATE_ADD(?, INTERVAL 1 MONTH)";
        List<Double> getSpendingForAUser = new ArrayList<>();

        try (Connection connection = dbConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDate(2, startingDate);
            pstmt.setDate(3, startingDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                getSpendingForAUser.add(rs.getDouble("food_price"));
            }
        } catch (SQLException e) {
            System.err.println("Error calculating monthly spending for user: " + e.getMessage());
        }
        return getSpendingForAUser;
    }


    /**
     * @author :Melina
     */
    @Override
    public List<Food> getFoodsForUserByDate(int userId) {
        String sql = "SELECT * FROM user_foods WHERE user_id = ? ORDER BY date_consumed";
        List<Food> foods = new ArrayList<>();

        try (Connection connection = dbConnectionFactory.getConnection();
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


    @Override
    public List<Food> getAllFoodsForAUser(int userId) {
        String sql = "SELECT * FROM user_foods WHERE user_id = ?";
        List<Food> foods = new ArrayList<>();
        try (Connection connection = dbConnectionFactory.getConnection();
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

    @Override
    public int getAllFoodEntriesForAWeeklyPeriod(java.sql.Date startingDate) {
        String sql = "SELECT COUNT(*) FROM user_foods WHERE date_consumed BETWEEN DATE_SUB(?, INTERVAL 6 DAY) AND ?";
        try(Connection connection = dbConnectionFactory.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setDate(1, (Date) startingDate);
            pstmt.setDate(2, (Date) startingDate);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @author :Amina
     */
    @Override
    public boolean addFood(Food food) {//testuar, punon
        User loggedInUser = UserSession.getLoggedInUser();
        if(loggedInUser != null){
            java.sql.Date currentDate = Calendar.getCurrentDate();
            String sql = "INSERT INTO user_foods (food_name, calorie_value, food_price, date_consumed, user_id) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = dbConnectionFactory.getConnection();
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

    /**
     * @author :Amina
     */
    @Override
    public boolean deleteFood(int id) {
        String sql = "DELETE FROM user_foods WHERE user_food_id = ?";
        try (Connection connection = dbConnectionFactory.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting food: " + e.getMessage());
            return false;
        }
    }

    /**
     * @author :Amina
     */
    @Override
    public boolean updateFood(Food food) {
        String sql="UPDATE user_foods SET food_name=?, calorie_value=?, food_price=?, date_consumed=? WHERE user_food_id=?";
        try(Connection conn = dbConnectionFactory.getConnection();
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

    /**
     * @author :Amina
     */
    @Override
    public List<Double> getTodaysTotalCalories(int userId) { //testuar, punon
        String sql = "Select calorie_value from user_foods where user_id = ? and date_consumed = ?";
        List<Double> calories = new ArrayList<>();
        try(Connection conn = dbConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, userId);
            pst.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                calories.add(rs.getDouble("calorie_value"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return calories;
    }
}
