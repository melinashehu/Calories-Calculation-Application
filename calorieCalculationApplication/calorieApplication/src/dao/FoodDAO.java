package dao;

import entity.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public interface FoodDAO {
    public Food getFood(int id);
    public List<Food> getAllFoodsFromAWeeklyPeriodForAUser(int userId, Date startingDate);
    public List<Food> getAllFoodsForAUser(int userId);
    public List<Food> getAllFoodsForAWeeklyPeriod(Date startingDate);
    public boolean addFood(Food food);
    public boolean deleteFood(int id);

}
