package service;
import dao.FoodDAOImplementation;
import dao.UserDAOImplementation;
import entity.Food;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import entity.StatisticalReport;
import entity.User;

public class UserService implements UserServiceInterface{

    private FoodDAOImplementation foodDAO;
    private UserDAOImplementation userDAO;

    /**
     * @author :Melina
     */
    public UserService() {
        this.foodDAO = new FoodDAOImplementation();
        this.userDAO = new UserDAOImplementation();
    }

    @Override
    public double calculateTotalCaloriesConsumedPerWeek(int userId,Date startingDate) {
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
    @Override
    public double calculateTotalMoneySpentPerWeek(int userId,Date startingDate) {
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
    @Override
    public int calculateDaysAboveCalorieThresholdPerWeek(int userId, double calorieThreshold) {
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
    @Override
    public double sumofTodaysTotalCalories(int userId){
        List<Double> todaysCalories = foodDAO.getTodaysTotalCalories(userId);
        double todaysTotalCaloriesCount = 0.0;
        for (double calorieValue : todaysCalories) {
            todaysTotalCaloriesCount += calorieValue;
        }
        return todaysTotalCaloriesCount;
    }

    @Override
    public double sumOfTotalMoneySpent(int userId, Date startingDate) {
        List<Double> totalMoney = foodDAO.getMoneySpentFromAUser(userId, startingDate);
        double totalMoneySpent = 0.0;
        for (double money : totalMoney) {
            totalMoneySpent += money;
        }
        return totalMoneySpent;
    }

    @Override
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
    @Override
    public StatisticalReport generateUserReport(int userId, Date startingDate, double calorieThreshold) {
        double totalCalories = calculateTotalCaloriesConsumedPerWeek(userId, startingDate);
        double totalSpending = calculateTotalMoneySpentPerWeek(userId, startingDate);
        int daysAboveThreshold = calculateDaysAboveCalorieThresholdPerWeek(userId, calorieThreshold);

        return new StatisticalReport(totalCalories, totalSpending, daysAboveThreshold);
    }

    @Override
    public List<User> getAllUsersService() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean deleteUserService(int userId){
        return userDAO.deleteUser(userId);
    }

    @Override
    public List<Integer> getAllUsersIdsService(){
        return userDAO.getAllUsersIds();
    }

    @Override
    public List<Boolean> getHasExceededMoneyLimitColumnService() {
        return userDAO.getHasExceededMoneyLimitColumn();
    }

    @Override
    public boolean addUserService(User user){
       return userDAO.addUser(user);
    }

    @Override
    public User getUserByEmailAndPasswordService(String email, String password){
        return userDAO.getUserByEmailAndPassword(email, password);
    }

    @Override
    public int getUserIdByEmailService(String email){
        return userDAO.getUserIdByEmail(email);
    }
}