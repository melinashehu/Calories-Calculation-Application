package Report;

import dao.FoodDAOImplementation;
import entity.Food;
import entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminReport {
    private final User user;
    private final List<Food> foods;
    private final FoodDAOImplementation foodDAO = new FoodDAOImplementation();
    private final double avgWeeklyConsumedCaloriesForAUser;

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

    public User getUser(){
        return user;
    }

    public List<Food> getFoods(){
        return foods;
    }

    public double getAvgWeeklyConsumedCaloriesForAUser() {
        return avgWeeklyConsumedCaloriesForAUser;
    }

    public void compareFoodEntriesPerWeek(){
        LocalDate today = LocalDate.now();
        Date currentDate = Date.valueOf(today);
        Date preivousWeekStartDate = Date.valueOf(today.minusWeeks(1));

        int currentWeekEntries = foodDAO.getAllFoodEntriesForAWeeklyPeriod(currentDate);
        int previousWeekEntries = foodDAO.getAllFoodEntriesForAWeeklyPeriod(preivousWeekStartDate);

        if(currentWeekEntries > previousWeekEntries){
            System.out.println("This week has "+(currentWeekEntries - previousWeekEntries)+ "more entries compared to the previous week.");
        }else if(currentWeekEntries < previousWeekEntries){
            System.out.println("This week has "+(previousWeekEntries - currentWeekEntries)+" fewer entries compared to the previous week.");
        }else{
            System.out.println("The number of entries is the same as the previous week.");
        }
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
