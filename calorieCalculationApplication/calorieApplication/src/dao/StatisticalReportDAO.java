package Report;

public interface ReportCalculation {
    double calculateTotalCaloriesConsumed(int userId);
    double calculateTotalSpendingMoney(int userId);
    int calculateDaysAboveCalorieThreshold(int userId, double calorieThreshold);
    double calculateMonthlySpendingForAUser(int userId, java.sql.Date startingDate);
}
