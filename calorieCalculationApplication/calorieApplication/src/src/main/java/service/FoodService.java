package service;
/**
 * This class serves as a service class to FoodDAO.
 */

import dao.FoodDAOImplementation;
import entity.Food;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Amina
 */
public class FoodService implements FoodServiceInterface {
    private FoodDAOImplementation foodDAO;

    public FoodService() {
        this.foodDAO = new FoodDAOImplementation();
    }

    public List<Food> filterFoodByDateRange(int userId, LocalDate start, LocalDate end) {
        List<Food> allFoods = foodDAO.getAllFoodsForAUser(userId);
        List<Food> filteredFoods = new ArrayList<>();
        for (Food food : allFoods) {
            LocalDate dateConsumed = food.getDateWhenConsumed().toLocalDate();
            if (dateConsumed.isAfter(start) && dateConsumed.isBefore(end)) {
                filteredFoods.add(food);
            }
        }
        return filteredFoods;
    }

    public List<Food> getAllFoodsFromAWeeklyPeriodForAUserService(int userId, java.sql.Date startingDate){
        return foodDAO.getAllFoodsFromAWeeklyPeriodForAUser(userId,startingDate);
    }

    public List<Food> getAllFoodsForAUserService(int userId){
        return foodDAO.getAllFoodsForAUser(userId);
    }

    public boolean updateFoodService(Food food){
        return foodDAO.updateFood(food);
    }
    public boolean addFoodService(Food food){
        return foodDAO.addFood(food);
    }

}
