package controller;

/**
 * This class serves as a controller class for AllFoods.fxml view and prints in a table every food entry for a LOGGED user.
 * Implementation is highly-based at FoodListPerUserController.
 * Do NOT change this class unless for big/debugging changes. Any inappropriate change may result in a functionality failure.
 */

import dao.FoodDAOImplementation;
import entity.Food;
import entity.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import login.UserSession;
import service.FoodService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: Amina
 */
public class AllFoodsController {
    @FXML
    private TableView<Food> foodTable;
    @FXML
    private TableColumn<Food,Integer> userIdCol;
    @FXML
    private TableColumn<Food,Integer> userFoodIdCol;
    @FXML
    private TableColumn<Food,String> userFoodNameCol;
    @FXML
    private TableColumn<Food, Double> calorieValueCol;
    @FXML
    private TableColumn<Food,Double> foodPriceCol;
    @FXML
    private TableColumn<Food, Date> dateConsumedCol;
    @FXML
    private TextField userNameTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button filterButton;
    private FoodDAOImplementation foodDao = new FoodDAOImplementation();
    private FoodService foodService = new FoodService();

    public void displayUserGreeting(User loggedUser){
        if(loggedUser != null){
            userNameTextField.setText(loggedUser.getUserName() + "'s list of foods:");
        } else {
            userNameTextField.setText("");
        }
    }

    public void initializeData() {
        User loggedUser = UserSession.getLoggedInUser();
        if(loggedUser == null){
            throw new IllegalArgumentException("The user cannot be null!");
        }
        displayUserGreeting(loggedUser);
        loadFoodData();
    }

    private void loadFoodData(){
        User loggedUser = UserSession.getLoggedInUser();
        if(loggedUser == null){
            throw new IllegalArgumentException("The user cannot be null!");
        }
        int userId = loggedUser.getUserId();
        List<Food> userFoods = foodDao.getAllFoodsForAUser(userId);
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList(userFoods);
        foodTable.setItems(foodObservableList);
    }

    @FXML
    private void filterByDate(){
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        User loggedUser = UserSession.getLoggedInUser();
        int userId = loggedUser.getUserId();
        if(startDate == null){
            System.out.println("Please select a start date!");
            return;
        }

        List<Food> filteredFoods = foodService.filterFoodByDateRange(userId, startDate, endDate);
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList(filteredFoods);
        foodTable.setItems(foodObservableList);
    }

    @FXML
    public void initialize(){
        User loggedUser = UserSession.getLoggedInUser();
        if (loggedUser != null) {
            initializeData();
        } else {
            System.out.println("No logged user!");
        }

        userIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUserId()).asObject());
        userFoodIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFoodId()).asObject());
        userFoodNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodName()));
        calorieValueCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCalorie()).asObject());
        foodPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        dateConsumedCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateWhenConsumed()));
    }
}
