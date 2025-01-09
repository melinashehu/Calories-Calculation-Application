package service;

import Report.AdminReport;
import dao.StatisticalReportDAOImplementation;
import Report.AdminReport;
import dao.FoodDAOImplementation;
import dao.UserDAOImplementation;
import entity.Food;
import entity.User;
import login.UserSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private final UserDAOImplementation userDAO;
    private final FoodDAOImplementation foodDAO;
    private StatisticalReportDAOImplementation reportCalculation;

    public AdminService(){
        this.foodDAO = new FoodDAOImplementation();
        this.userDAO = new UserDAOImplementation();
        this.reportCalculation = new StatisticalReportDAOImplementation();
    }

    private boolean isAdmin() {//kontrollimi nese user-i eshte admin
        User loggedInUser = UserSession.getLoggedInUser();
        return loggedInUser != null && "admin".equals(loggedInUser.getRole());
    }

    /**
     * author:Edna
     */
    public AdminReport generateWeeklyFoodReport(int userId, Date startingDate){//testuar dhe funksionon

        if(!isAdmin()){
            throw new SecurityException("You have no access to this information!");
        }

        double avgWeeklyConsumedCaloriesForAUser;
        double sumOfCalorieValuesForAWeeklyPeriodForAUser = 0.0;
        List<Food> foods = foodDAO.getAllFoodsFromAWeeklyPeriodForAUser(userId,startingDate);
            List<Double> calorieValues = foodDAO.getCalorieValuesForAWeeklyPeriodForAUser(userId,startingDate);
            /*for(double calorie : calorieValues){
                sumOfCalorieValuesForAWeeklyPeriodForAUser += calorie;
            }*/
        sumOfCalorieValuesForAWeeklyPeriodForAUser = calorieValues.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

            avgWeeklyConsumedCaloriesForAUser = sumOfCalorieValuesForAWeeklyPeriodForAUser/foods.size();
            User user = userDAO.getUserById(userId);
            return new AdminReport(user,foods,avgWeeklyConsumedCaloriesForAUser);

    }

    /**
     * author:Edna
     */

    public List<AdminReport> usersWhoExceededMonthlySpendingLimit(Date startingDate){//testuar dhe funksionon
        if(!isAdmin()){
            throw new SecurityException("You have no access to this information!");
        }
        double monthlyExpenditureThreshold = 1000;
        List<AdminReport> usersWhoExceededMonthlySpendingLimit = new ArrayList<>();
        List<Integer> allUsersIds = userDAO.getAllUsersIds();
        double monthlySpendingForAUser = 0.0;

        for(Integer userId : allUsersIds){
            List<Double> moneySpentFromAUser = reportCalculation.getMoneySpentFromAUser(userId,startingDate);

            for(Double moneySpent : moneySpentFromAUser) {
                monthlySpendingForAUser += moneySpent;
            }

            if(monthlySpendingForAUser > monthlyExpenditureThreshold){
                User user = userDAO.getUserById(userId);
                usersWhoExceededMonthlySpendingLimit.add(new AdminReport(user));
            }else System.out.println("No user has exceeded the monthly expenditure.");
        }
        return usersWhoExceededMonthlySpendingLimit;
    }
}
