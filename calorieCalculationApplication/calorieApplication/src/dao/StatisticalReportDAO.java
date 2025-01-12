package dao;

import java.util.List;

public interface StatisticalReportDAO {
    double calculateTotalCaloriesConsumed(int userId);
    double calculateTotalSpendingMoney(int userId);
    int calculateDaysAboveCalorieThreshold(int userId, double calorieThreshold);

    /**
     * @author :Edna
     */
    List<Double> getMoneySpentFromAUser(int userId, java.sql.Date startingDate);
}
