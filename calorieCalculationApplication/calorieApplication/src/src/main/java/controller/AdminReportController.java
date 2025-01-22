package controller;

import entity.Food;
import entity.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import service.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class AdminReportController {
    @FXML
    public TextArea foodEntriesComparisonArea;
    @FXML
    public BarChart<String, Number> barChart;
    @FXML
    public TableView<User> userTable;
    @FXML
    public TableColumn<User, String> userIdColumn;
    @FXML
    public TableColumn<User, Boolean> moneyLimitColumn;
    private AdminServiceInterface adminService;
    private FoodServiceInterface foodService;
    private UserServiceInterface userService;

    //dependency injection
    public AdminReportController(AdminServiceInterface adminService,FoodServiceInterface foodService,UserServiceInterface userService){
        this.adminService = adminService;
        this.foodService = foodService;
        this.userService = userService;
    }

    @FXML
    public void initialize() {
        showEntriesComparison();
        showCaloriesChart();
        showUsersThatExceededMoneyLimit();
    }

    @FXML
    public void showEntriesComparison() {
        String report = adminService.printFoodEntriesPerWeekComparison();
        foodEntriesComparisonArea.setText(report);
    }

    @FXML
    public void showCaloriesChart(){
        List<Integer> userIds = userService.getAllUsersIdsService();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Avg Calories per User");
        LocalDate today = LocalDate.now();
        Date startDate = Date.valueOf(today.minusDays(7));
        for(Integer userId : userIds) {
            double totalCalories = userService.calculateTotalCaloriesConsumedPerWeek(userId, startDate);
            List<Food> foodEntries = foodService.getAllFoodsFromAWeeklyPeriodForAUserService(userId, startDate);
            int entryCount = foodEntries.size();
            double avgCalories = entryCount > 0 ? totalCalories / entryCount : 0.0;
            series.getData().add(new XYChart.Data<>(userId.toString(), avgCalories));
        }
        barChart.getData().clear();
        barChart.getData().add(series);
    }

    @FXML
    public void showUsersThatExceededMoneyLimit() {
        List<Integer> userIds = userService.getAllUsersIdsService();
        List<Boolean> exceededMoneyLimit = userService.getHasExceededMoneyLimitColumnService();
        ObservableList<User> userData = FXCollections.observableArrayList();
        for (int i = 0; i < userIds.size(); i++) {
            userData.add(new User(userIds.get(i), exceededMoneyLimit.get(i)));
        }
        userIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserId()).asString());
        moneyLimitColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHasExceededMoneyLimit()));
        userTable.setItems(userData);
    }


}
