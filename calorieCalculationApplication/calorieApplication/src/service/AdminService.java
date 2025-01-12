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
import java.time.LocalDate;
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
     * @author :Edna
     */

    public List<AdminReport> usersWhoExceededMonthlySpendingLimit(Date startingDate){//testuar dhe funksionon
        if(!isAdmin()){
            throw new SecurityException("You have no access to this information!");
        }
        double monthlyExpenditureThreshold = 1000;
        List<AdminReport> usersWhoExceededMonthlySpendingLimit = new ArrayList<>();
        List<Integer> allUsersIds = userDAO.getAllUsersIds();

        for(Integer userId : allUsersIds){
            double monthlySpendingForAUser = 0.0;
            List<Double> moneySpentForAUser = reportCalculation.getMoneySpentFromAUser(userId,startingDate);
            for(double moneySpent : moneySpentForAUser){
                monthlySpendingForAUser += moneySpent;
            }

            if(monthlySpendingForAUser > monthlyExpenditureThreshold){
                User user = userDAO.getUserById(userId);
                usersWhoExceededMonthlySpendingLimit.add(new AdminReport(user));
            }
        }

        if(usersWhoExceededMonthlySpendingLimit.isEmpty()){
            System.out.println("No user has exceeded the monthly expenditure.");
        }
        return usersWhoExceededMonthlySpendingLimit;
    }

    /**
     *
     * @author: Amina
     */
    public List<User> getAvgCaloriesPerUserLast7Days(){ //punon ne console

        if(!isAdmin()){
            throw new SecurityException("You have no access to this information!");
        }

        List<User> reportList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Date startDate = Date.valueOf(today.minusDays(7));
        try{
            List<User> users = userDAO.getAllUsers();
            for(User user : users){
                List<Food> userFoods = foodDAO.getAllFoodsFromAWeeklyPeriodForAUser(user.getUserId(), startDate);
                double totalCalories = userFoods.stream().mapToDouble(Food::getCalorie).sum();
                double avgCalories = userFoods.isEmpty() ? 0.0 : totalCalories / userFoods.size();
                reportList.add(new User(user.getUserName(), avgCalories));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return reportList;
    }

    /**
     *
     * @author: Amina
     */
    public String printFoodEntriesPerWeekComparison(){
        LocalDate today = LocalDate.now();
        Date currentDate = Date.valueOf(today);
        Date preivousWeekStartDate = Date.valueOf(today.minusWeeks(1));

        int currentWeekEntries = foodDAO.getAllFoodEntriesForAWeeklyPeriod(currentDate);
        int previousWeekEntries = foodDAO.getAllFoodEntriesForAWeeklyPeriod(preivousWeekStartDate);

        StringBuilder builder = new StringBuilder();
        builder.append("Current week entries: ").append(currentWeekEntries);
        builder.append("\nPrevious week entries: ").append(previousWeekEntries);

        if(currentWeekEntries > previousWeekEntries){
            builder.append("\nThis week has ").append((currentWeekEntries - previousWeekEntries)).append("more entries compared to the previous week.");
        }else if(currentWeekEntries < previousWeekEntries){
            builder.append("\nThis week has ").append((previousWeekEntries - currentWeekEntries)).append(" fewer entries compared to the previous week.");
        }else{
            builder.append("\nThe number of entries is the same as the previous week.");
        }

        return builder.toString();
    }
}
