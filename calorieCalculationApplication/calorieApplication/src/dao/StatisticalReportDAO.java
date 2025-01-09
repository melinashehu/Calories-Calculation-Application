package dao;

import java.util.List;

public interface StatisticalReportDAO {
    double calculateTotalCaloriesConsumed(int userId);
    double calculateTotalSpendingMoney(int userId);
    int calculateDaysAboveCalorieThreshold(int userId, double calorieThreshold);
    List<Double> getSpendingForAUser(int userId, java.sql.Date startingDate);
}
