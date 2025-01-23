package service;
import entity.*;

import java.sql.Date;
import java.util.List;

public interface UserServiceInterface {
    public double calculateTotalCaloriesConsumedPerWeek(int userId, Date startingDate);
    public double calculateTotalMoneySpentPerWeek(int userId, Date startingDate);
    public int calculateDaysAboveCalorieThresholdPerWeek(int userId, double calorieThreshold);
    public double sumofTodaysTotalCalories(int userId);
    public double sumOfTotalMoneySpent(int userId, Date startingDate);
    public void updateHasExceededLimitForAllUsers();
    public StatisticalReport generateUserReport(int userId, Date startingDate, double calorieThreshold);
    public List<User> getAllUsersService();
    public boolean deleteUserService(int userId);
    public List<Integer> getAllUsersIdsService();
    public List<Boolean> getHasExceededMoneyLimitColumnService();
    public boolean addUserService(User user);
    public User getUserByEmailAndPasswordService(String email, String password);
    public int getUserIdByEmailService(String email);
}
