package controller;
import calendar.Calendar;
import dao.FoodDAO;
import dao.FoodDAOImplementation;
import entity.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.StatisticalReportService;
import login.UserSession;
import java.io.IOException;
import java.sql.Date;


public class HomeController {
    @FXML private Label totalCaloriesLabel;
    @FXML private Label totalSpendingLabel;
    @FXML private Label daysAboveThresholdLabel;
    @FXML private TextField foodNameField;
    @FXML private TextField calorieField;
    @FXML private TextField priceField;
    @FXML private DatePicker dateConsumedField;

    private StatisticalReportService reportService = new StatisticalReportService();

    @FXML
    private void initialize() {
        updateReport();
    }

    private void updateReport() {
        int userId = UserSession.getLoggedInUser().getUserId();
        double calorieThreshold = 2500.0;


        var report = reportService.generateReport(userId, calorieThreshold);


        totalCaloriesLabel.setText(String.valueOf(report.getTotalCalories()));
        totalSpendingLabel.setText(String.valueOf(report.getTotalSpendingMoney()));
        daysAboveThresholdLabel.setText(String.valueOf(report.getDaysAboveCalorieThreshold()));
    }


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
            updateReport();
            clearFormFields();
        } else {
            System.out.println("Error adding food.");
        }
    }

    private void clearFormFields() {
        foodNameField.clear();
        calorieField.clear();
        priceField.clear();

    }


}
