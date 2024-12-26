package service;
import dao.FoodDAOImplementation;
import dao.FoodDAO;
import entity.Food;
import entity.StatisticalReport;

import java.util.Date;
import java.util.List;

public class SatisticalReportService {
    private FoodDAO foodDAO;

    public SatisticalReportService() {
        this.foodDAO = new FoodDAOImplementation() {

        };
    }
//gjenerimi i raportit
    public StatisticalReport generateReport(int userId, double calorieThreshold) {
        double totalCalories = foodDAO.calculateTotalCaloriesConsumed(userId);
        double totalSpending = foodDAO.calculateTotalSpendingMoney(userId);
        int daysAboveThreshold = foodDAO.calculateDaysAboveCalorieThreshold(userId, calorieThreshold);

        return new StatisticalReport(userId, totalCalories, totalSpending, daysAboveThreshold);
    }
}
