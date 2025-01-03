package Report;

import entity.Food;
import entity.User;
import java.util.List;

public class AdminReport {

    private final User user;
    private final List<Food> foods;
    private final double avgWeeklyConsumedCaloriesForAUser;

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
