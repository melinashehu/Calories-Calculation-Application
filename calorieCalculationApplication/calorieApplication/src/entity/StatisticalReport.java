package entity;

/**
 * Represents a user's report in the system.
 * This class should NOT be modified without careful consideration.
 * Any changes to this class can impact the other parts of the application.
 */


/**
 * @author :Melina
 */

public class StatisticalReport {
    private int userId;
    private double totalCalories;
    private double totalSpendingMoney;
    private int daysAboveCalorieThreshold;
    public StatisticalReport() {}


    public StatisticalReport(double totalCalories, double totalSpendingMoney, int daysAboveCalorieThreshold) {
        this.totalCalories = totalCalories;
        this.totalSpendingMoney = totalSpendingMoney;
        this.daysAboveCalorieThreshold = daysAboveCalorieThreshold;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalSpendingMoney() {
        return totalSpendingMoney;
    }

    public int getDaysAboveCalorieThreshold() {
        return daysAboveCalorieThreshold;
    }

    @Override
    public String toString() {
        return "StatisticalReport{" +
                "userId=" + userId +
                ", totalCalories=" + totalCalories +
                ", totalSpendingMoney=" + totalSpendingMoney +
                ", daysAboveCalorieThreshold=" + daysAboveCalorieThreshold +
                '}';
    }
}