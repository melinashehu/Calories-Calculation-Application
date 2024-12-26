package service;

import entity.StatisticalReport;



public class SatisticalReportService {
    private ReportCalculation reportCalculation;

    public StatisticalReportService() {
        this.reportCalculation = new ReportCalculationImplementation();
    }

    // Gjenerimi i raportit
    public StatisticalReport generateReport(int userId, double calorieThreshold) {
        double totalCalories = reportCalculation.calculateTotalCaloriesConsumed(userId);
        double totalSpending = reportCalculation.calculateTotalSpendingMoney(userId);
        int daysAboveThreshold = reportCalculation.calculateDaysAboveCalorieThreshold(userId, calorieThreshold);

        return new StatisticalReport(userId, totalCalories, totalSpending, daysAboveThreshold);
    }
}
