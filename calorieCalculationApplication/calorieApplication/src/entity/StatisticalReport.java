package entity;

public class StatisticalReport {
    private int userId;
    private double totalCaloriesConsumed;
    private double totalSpendingMoney;
    private int daysAboveCalorieThreshold;

    public StatisticalReport() {}

    public StatisticalReport(int userId, double totalCaloriesConsumed, int daysAboveCalorieThreshold) {
        this.userId = userId;
        this.totalCaloriesConsumed= totalCaloriesConsumed;
        this.totalSpendingMoney = totalSpendingMoney;
        this.daysAboveCalorieThreshold = daysAboveCalorieThreshold;
    }

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    public double getTotalCalories() {return totalCaloriesConsumed;}
    public void setTotalCalories(double totalCalories) {this.totalCaloriesConsumed = totalCaloriesConsumed;}

    public double getTotalSpendingMoney() {return totalSpendingMoney;}
    public void setTotalSpendingMoney(double totalSpendingMoney) {this.totalSpendingMoney = totalSpendingMoney;}

    public int getDaysAboveCalorieThreshold() {return daysAboveCalorieThreshold;}
    public void setDaysAboveCalorieThreshold(int daysAboveCalorieThreshold) {this.daysAboveCalorieThreshold = daysAboveCalorieThreshold;}

    public String toString() {
        return "Statistical Report for " + userId + ": total calories consumed=" + totalCaloriesConsumed + ", days when the calorie limit was surpassed= " + daysAboveCalorieThreshold + ", spending money= " + totalSpendingMoney;
    }


}