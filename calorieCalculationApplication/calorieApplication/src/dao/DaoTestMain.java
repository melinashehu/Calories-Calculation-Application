package dao;

import dao.UserDAOImplementation;
import entity.*;

import java.util.List;

public class DaoTestMain {
    public static void main(String[] args) {
        FoodDAOImplementation foodDao = new FoodDAOImplementation();
        List<Food> foods = foodDao.getAllFoodsForAUser(2);
        for (Food food : foods) {
            System.out.println(food);
        }
    }
}
