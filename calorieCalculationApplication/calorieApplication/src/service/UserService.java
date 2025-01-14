package service;
import dao.FoodDAOImplementation;
import entity.Food;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import entity.StatisticalReport;

public class UserService {
    private FoodDAOImplementation foodDAO;

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
     * @author :Melina
     */
    public StatisticalReport generateUserReport(int userId, Date startingDate, double calorieThreshold) {
        double totalCalories = calculateTotalCaloriesConsumedPerWeek(userId, startingDate);
        double totalSpending = calculateTotalMoneySpentPerWeek(userId, startingDate);
        int daysAboveThreshold = calculateDaysAboveCalorieThresholdPerWeek(userId, calorieThreshold);

        return new StatisticalReport(totalCalories, totalSpending, daysAboveThreshold);
    }
}
