package Report;

import dao.FoodDAOImplementation;
import dao.UserDAOImplementation;
import entity.Food;
import entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminReport {
    private final User user;
    private final List<Food> foods;
    private final double avgWeeklyConsumedCaloriesForAUser;
    private boolean exceededSpending;

    public AdminReport(){
        this.user = null;
        this.foods = null;
        this.avgWeeklyConsumedCaloriesForAUser = 0.0;
    }

    public AdminReport(User user, List<Food> foods, double avgWeeklyConsumedCaloriesForAUser) {
        this.user = user;
        this.avgWeeklyConsumedCaloriesForAUser = avgWeeklyConsumedCaloriesForAUser;
        this.foods = foods;
    }

    public AdminReport(User user){
        this.user = user;
        this.foods = null;
        this.avgWeeklyConsumedCaloriesForAUser = 0.0;
    }

    public boolean isExceededSpending() {
        return exceededSpending;
    }

    public void setExceededSpending(boolean exceededSpending) {
        this.exceededSpending = exceededSpending;
    }

    public User getUser(){
        return user;
    }

    public List<Food> getFoods(){
        return foods;
    }

    public double getAvgWeeklyConsumedCaloriesForAUser() {
        return avgWeeklyConsumedCaloriesForAUser;
    }

    @Override
    public String toString() {
        return "User report{" +
                "User=" + user.getUserName() +
                ", foods=" + foods +
                ", Average calories consumed=" + avgWeeklyConsumedCaloriesForAUser +
                '}';
    }

}
