package service;

import entity.Food;

import java.time.LocalDate;
import java.util.List;

public interface FoodServiceInterface {
    public List<Food> filterFoodByDateRange(int userId, LocalDate start, LocalDate end);
    public List<Food> getAllFoodsFromAWeeklyPeriodForAUserService(int userId, java.sql.Date startingDate);
    public List<Food> getAllFoodsForAUserService(int userId);
    public boolean updateFoodService(Food food);
    public boolean addFoodService(Food food);
}
