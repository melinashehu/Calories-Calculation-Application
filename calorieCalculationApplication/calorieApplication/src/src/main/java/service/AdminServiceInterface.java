package service;
import entity.*;
import java.sql.Date;
import java.util.List;
public interface AdminServiceInterface {
    public List<AdminReport> usersWhoExceededMonthlySpendingLimit(Date startingDate);
    public List<User> getAvgCaloriesPerUserLast7Days();
    public String printFoodEntriesPerWeekComparison();
}
