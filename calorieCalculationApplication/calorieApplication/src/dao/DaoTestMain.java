package dao;

import dao.UserDAOImplementation;
import entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DaoTestMain {
    public static void main(String[] args) {
        /*FoodDAOImplementation foodDao = new FoodDAOImplementation();
        java.sql.Date testDate = new Date(2025 - 1900, 0, 1);
        int entries = foodDao.getAllFoodEntriesForAWeeklyPeriod(testDate);
        System.out.println(entries);
        System.out.println(foodDao.getMoneySpentFromAUser(2,testDate));
        System.out.println(foodDao.getFoodsForUserByDate(2));
        System.out.println(foodDao.getTodaysTotalCalories(2));*/

        UserDAOImplementation userDAO = new UserDAOImplementation();
        System.out.println(userDAO.getHasExceededMoneyLimitColumn());
    }
}
