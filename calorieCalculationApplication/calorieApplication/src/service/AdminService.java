package service;

import Report.ReportCalculationImplementation;
import Report.UserReport;
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
    private ReportCalculationImplementation reportCalculation;

    public AdminService(){
        this.foodDAO = new FoodDAOImplementation();
        this.userDAO = new UserDAOImplementation();
        this.reportCalculation = new ReportCalculationImplementation();
    }

    private boolean isAdmin() {//kontrollimi nese user-i eshte admin
        User loggedInUser = UserSession.getLoggedInUser();
        return loggedInUser != null && "admin".equals(loggedInUser.getRole());
    }

    public UserReport generateWeeklyFoodReport(int userId, Date startingDate){

        if(!isAdmin()){
            throw new SecurityException("You have no access to this information!");
        }

        double avgWeeklyConsumedCaloriesForAUser;

        List<Food> foods = foodDAO.getAllFoodsFromAWeeklyPeriodForAUser(userId,startingDate);
        avgWeeklyConsumedCaloriesForAUser = foodDAO.getAvgCalorieValueFromAWeeklyPeriodForAUser(userId, startingDate);
        User user = userDAO.getUserById(userId);
        return new UserReport(user,foods,avgWeeklyConsumedCaloriesForAUser);

    }

    public List<UserReport> usersWhoExceededMonthlySpendingLimit(Date startingDate){
        if(!isAdmin()){
            throw new SecurityException("You have no access to this information!");
        }
        double monthlyExpenditureThreshold = 1000;
        List<UserReport> usersWhoExceededMonthlySpendingLimit = new ArrayList<>();
        List<Integer> allUsersIds = userDAO.getAllUsersIds();

        for(Integer userId : allUsersIds){
            double monthlySpendingForAUser = reportCalculation.calculateMonthlySpendingForAUser(userId,startingDate);

            if(monthlySpendingForAUser > monthlyExpenditureThreshold){
                User user = userDAO.getUserById(userId);
                usersWhoExceededMonthlySpendingLimit.add(new UserReport(user));
            }else System.out.println("No user has exceeded the monthly expenditure.");
        }
        return usersWhoExceededMonthlySpendingLimit;
    }
}
