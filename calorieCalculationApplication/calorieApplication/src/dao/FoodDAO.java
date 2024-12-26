package dao;

import entity.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public interface FoodDAO {
    public Food getFood(int id);
    public List<Food> getAllFoodsFromAWeeklyPeriodForAUser(int userId, java.sql.Date startingDate);
    public List<Food> getAllFoodsForAUser(int userId);
    public List<Food> getAllFoodsForAWeeklyPeriod(Date startingDate);
    public boolean addFood(Food food);
    public boolean deleteFood(int id);

//    Duhet të shtojmë metoda të reja për të llogaritur:
//    Totalin e kalorive të konsumuara nga përdoruesi.
//    Shpenzimet totale të përdoruesit për ushqime.
//    Numrin e ditëve kur kalorive kanë kaluar një kufi të caktuar
     double calculateTotalCaloriesConsumed(int userId);
    double calculateTotalSpendingMoney(int userId);
    int calculateDaysAboveCalorieThreshold(int userId, double calorieThreshold);

}
