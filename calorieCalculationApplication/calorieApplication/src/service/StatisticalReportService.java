package service;

import dao.StatisticalReportDAO;
import dao.StatisticalReportDAOImplementation;
import entity.StatisticalReport;

public class StatisticalReportService {
    private StatisticalReportDAO reportCalculation;

    public StatisticalReportService() {
        this.reportCalculation = new StatisticalReportDAOImplementation();
    }

    public StatisticalReport generateUserReport(int userId, double calorieThreshold) {
        double totalCalories = reportCalculation.calculateTotalCaloriesConsumed(userId);
        double totalSpending = reportCalculation.calculateTotalSpendingMoney(userId);
        int daysAboveThreshold = reportCalculation.calculateDaysAboveCalorieThreshold(userId, calorieThreshold);

        return new StatisticalReport(userId, totalCalories, totalSpending, daysAboveThreshold);
    }
}
