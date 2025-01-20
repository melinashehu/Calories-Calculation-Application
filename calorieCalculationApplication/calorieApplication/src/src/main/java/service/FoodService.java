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
public class FoodService {
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
}
