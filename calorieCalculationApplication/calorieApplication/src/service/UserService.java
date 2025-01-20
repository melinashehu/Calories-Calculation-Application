package service;
import dao.FoodDAOImplementation;
import dao.UserDAOImplementation;
import dao.*;
import entity.Food;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import entity.StatisticalReport;
import entity.User;

public class UserService {
    private FoodDAOImplementation foodDAO;
    private UserDAOImplementation userDAO = new UserDAOImplementation();

    /**
     * @author :Melina
     */
    public UserService() {
        this.foodDAO = new FoodDAOImplementation();
    }

    public double calculateTotalCaloriesConsumedPerWeek(int userId,Date startingDate) { //testuar, punon
        List<Double> calorieValues = foodDAO.getCalorieValuesForAWeeklyPeriodForAUser(userId, startingDate);
        double totalCalories = 0.0;
        for (double calorie : calorieValues) {
            totalCalories += calorie;
        }
        return totalCalories;
    }


    /**
     * @author :Melina
     */
    public double calculateTotalMoneySpentPerWeek(int userId,Date startingDate) { //testuar, punon
        List<Double> moneySpentValues = foodDAO.getMoneySpentFromAUser(userId, startingDate);
        double totalMoneySpent = 0.0;
        for (double money : moneySpentValues) {
            totalMoneySpent += money;
        }
        return totalMoneySpent;
    }


    /**
     * @author :Melina
     */
    public int calculateDaysAboveCalorieThresholdPerWeek(int userId, double calorieThreshold) {//testuar, punon
        List<Food> foods = foodDAO.getFoodsForUserByDate(userId);
        Map<Date, Double> dailyCalories = new HashMap<>();
        for (Food food : foods) {
            java.util.Date utilDate = food.getDateWhenConsumed();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            dailyCalories.put(sqlDate, dailyCalories.getOrDefault(sqlDate, 0.0) + food.getCalorie());
        }
        int daysAboveThreshold = 0;
        for (double totalCalories : dailyCalories.values()) {
            if (totalCalories > calorieThreshold) {
                daysAboveThreshold++;
            }
        }
        return daysAboveThreshold;
    }


    /**
     * @author :Amina
     */
    public double sumofTodaysTotalCalories(int userId){ //testuar, punon
        List<Double> todaysCalories = foodDAO.getTodaysTotalCalories(userId);
        double todaysTotalCaloriesCount = 0.0;
        for (double calorieValue : todaysCalories) {
            todaysTotalCaloriesCount += calorieValue;
        }
        return todaysTotalCaloriesCount;
    }
    public double sumOfTotalMoneySpent(int userId, Date startingDate) {
        List<Double> totalMoney = foodDAO.getMoneySpentFromAUser(userId, startingDate);
        double totalMoneySpent = 0.0;
        for (double money : totalMoney) {
            totalMoneySpent += money;
        }
        return totalMoneySpent;
    }
    public void updateHasExceededLimitForAllUsers(){
        List<User> allUsers = userDAO.getAllUsers();
        Date startingDate = Date.valueOf(LocalDate.now().minusMonths(1));
        for(User user : allUsers){
            double totalSpent = sumOfTotalMoneySpent(user.getUserId(), startingDate);
            boolean hasExceededLimit = totalSpent > 1000;
            user.setHasExceededMoneyLimit(hasExceededLimit);
            userDAO.updateUser(user);
        }
    }



    /**
     * @author :Melina
     */
    public StatisticalReport generateUserReport(int userId, Date startingDate, double calorieThreshold) {
        double totalCalories = calculateTotalCaloriesConsumedPerWeek(userId, startingDate);
        double totalSpending = calculateTotalMoneySpentPerWeek(userId, startingDate);
        int daysAboveThreshold = calculateDaysAboveCalorieThresholdPerWeek(userId, calorieThreshold);

        return new StatisticalReport(totalCalories, totalSpending, daysAboveThreshold);
    }
}