package controller;
import entity.StatisticalReport;
import calendar.Calendar;
import dao.FoodDAO;
import dao.FoodDAOImplementation;
import entity.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.UserService;
import login.UserSession;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author :Melina
 */
public class HomeController {
    @FXML private Label totalCaloriesLabel;
    @FXML private Label totalSpendingLabel;
    @FXML private Label daysAboveThresholdLabel;
    @FXML private TextField foodNameField;
    @FXML private TextField calorieField;
    @FXML private TextField priceField;
    private UserService reportService = new UserService();

    @FXML
    private void initialize() {
        updateUserReport();
    }
    /**
     * @author :Melina
     */
    private void updateUserReport() {
        int userId = UserSession.getLoggedInUser().getUserId();
        double calorieThreshold = 2500.0;
        LocalDate weekStartDate = LocalDate.now().minusDays(7);
        Date sqlWeekStartDate = Date.valueOf(weekStartDate);
        StatisticalReport report = reportService.generateUserReport(userId, sqlWeekStartDate, calorieThreshold);

        totalCaloriesLabel.setText(String.valueOf(report.getTotalCalories()));
        totalSpendingLabel.setText(String.valueOf(report.getTotalSpendingMoney()));
        daysAboveThresholdLabel.setText(String.valueOf(report.getDaysAboveCalorieThreshold()));
    }

    /**
     * @author :Melina
     */
    @FXML
    private void handleAddFood(ActionEvent event) {
        String foodName = foodNameField.getText();
        double calorieValue = Double.parseDouble(calorieField.getText());
        double priceValue = Double.parseDouble(priceField.getText());
        Date dateConsumed = Calendar.getCurrentDate();

        Food newFood = new Food(0, UserSession.getLoggedInUser().getUserId(), foodName, calorieValue, priceValue, dateConsumed);

        FoodDAO foodDao = new FoodDAOImplementation();
        boolean added = foodDao.addFood(newFood);

        if (added) {
            updateUserReport();
            clearFormFields();
        } else {
            System.out.println("Error adding food.");
        }
    }/**
     * @author :Melina
     */

    private void clearFormFields() {
        foodNameField.clear();
        calorieField.clear();
        priceField.clear();

    }


}
