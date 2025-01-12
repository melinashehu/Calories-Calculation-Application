package dao;

import entity.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public interface FoodDAO {
    public List<Food> getAllFoods();
    public Food getFood(int id);
    public List<Food> getAllFoodsFromAWeeklyPeriodForAUser(int userId, java.sql.Date startingDate);
    public List<Food> getAllFoodsForAUser(int userId);
    public int getAllFoodEntriesForAWeeklyPeriod(java.sql.Date startingDate);
    public boolean addFood(Food food);
    public boolean deleteFood(int id);
    public boolean updateFood(Food food);
    public List<Double> getCalorieValuesForAWeeklyPeriodForAUser(int userId, java.sql.Date startingDate);
}
