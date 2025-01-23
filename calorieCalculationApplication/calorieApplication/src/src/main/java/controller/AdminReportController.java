package controller;

import dao.FoodDAOImplementation;
import dao.UserDAOImplementation;
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
import service.AdminService;
import service.FoodService;
import service.UserService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminReportController {
    @FXML
    private TextArea foodEntriesComparisonArea;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> userIdColumn;
    @FXML
    private TableColumn<User, Boolean> moneyLimitColumn;
    private AdminService adminService = new AdminService();
    private FoodService foodService = new FoodService();
    private UserService userService = new UserService();

    public AdminReportController(TextArea foodEntriesComparisonArea,BarChart<String,Number> barChart,TableView<User> userTable
    ,TableColumn<User,String> userIdColumn, TableColumn<User,Boolean> moneyLimitColumn) throws SQLException {
        this.foodEntriesComparisonArea = foodEntriesComparisonArea;
        this.barChart = barChart;
        this.userTable = userTable;
        this.userIdColumn = userIdColumn;
        this.moneyLimitColumn = moneyLimitColumn;
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
