package controller;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class FoodListPerUserController {

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

    private User selectedUser;
    FoodDAOImplementation foodDao = new FoodDAOImplementation();

    public void displayUserGreeting(User selectedUser){
        if(selectedUser!=null){
            userNameTextField.setText(selectedUser.getUserName()+"'s list of foods:");
        }else userNameTextField.setText("");
    }

    public void initializeData(User user) {
        if(user==null){
            throw new IllegalArgumentException("The user cannot be null!");
        }
        this.selectedUser = user;
        displayUserGreeting(user);
        loadFoodData();
    }

    private void loadFoodData(){
        List<Food> userFoods = foodDao.getAllFoodsForAUser(selectedUser.getUserId());
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList(userFoods);
        foodTable.setItems(foodObservableList);
    }

    @FXML
    public void initialize(){
        // Keto kolona nuk mund te editohen
        userIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUserId()).asObject());
        userFoodIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFoodId()).asObject());

        userFoodNameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFoodName()));
        userFoodNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        userFoodNameCol.setOnEditCommit(event -> {
            Food food = event.getRowValue();
            food.setFoodName(event.getNewValue());
            try {
                foodDao.updateFood(food);
            } catch (Exception e) {
                e.printStackTrace();

            }
        });

        calorieValueCol.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getCalorie()).asObject());
        calorieValueCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        calorieValueCol.setOnEditCommit(event -> {
            Food food = event.getRowValue();
            food.setCalorie(event.getNewValue());
            try {
                foodDao.updateFood(food);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        foodPriceCol.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        foodPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        foodPriceCol.setOnEditCommit(event -> {
            Food food = event.getRowValue();
            food.setPrice(event.getNewValue());
            try {
                foodDao.updateFood(food);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        dateConsumedCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>((java.sql.Date)cellData.getValue().getDateWhenConsumed()));

        dateConsumedCol.setCellFactory(column ->
                new TextFieldTableCell<>(new javafx.util.StringConverter<java.sql.Date>() {
                    @Override
                    public String toString(java.sql.Date date) {
                        return (date == null) ? "" : date.toLocalDate().toString(); // Convert java.sql.Date to LocalDate
                    }

                    @Override
                    public java.sql.Date fromString(String string) {
                        return (string == null || string.isEmpty()) ? null : java.sql.Date.valueOf(LocalDate.parse(string));
                    }
                }));

        dateConsumedCol.setOnEditCommit((TableColumn.CellEditEvent<Food, Date> event) -> {
            Food food = event.getRowValue();
            food.setDateWhenConsumed(event.getNewValue());
            try {
                foodDao.updateFood(food);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
