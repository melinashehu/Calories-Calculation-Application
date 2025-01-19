package controller;
import com.jfoenix.controls.JFXButton;

import dao.UserDAOImplementation;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Report.*;
import javafx.scene.text.Text;
import service.AdminService;
import service.UserService;


public class AdminController implements Initializable {

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, Integer> userIdCol;
    @FXML
    private TableColumn<User, String> userNameCol;
    @FXML
    private TableColumn<User, String> userEmailCol;
    @FXML
    private TableColumn<User, JFXButton> foodsCol;
    @FXML
    private TableColumn<User, JFXButton> deleteUserCol;
    @FXML
    private JFXButton refreshReportButton;
    @FXML
    private Text reportText;
    @FXML
    private TableView<User> caloriesTable;
    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, Double> avgCaloriesColumn;
    @FXML
    private TableColumn<User, Boolean> limitExceededColumn;
    private final AdminService adminService = new AdminService();
    UserDAOImplementation userDAO = new UserDAOImplementation();

    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadUserData();
            loadAdminReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUserData() {

        refreshTable();
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        userEmailCol.setCellValueFactory(new PropertyValueFactory<>("UserEmail"));
        try {
            List<User> users = userDAO.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("There are no users.");
            }
            ObservableList<User> userObservableList = FXCollections.observableArrayList();
            for (User user : users) {
                User userData = new User(user.getUserId(), user.getUserName(), user.getUserEmail());
                userObservableList.add(userData);
            }

            usersTable.setItems(userObservableList);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was an error loading the users' data.");
        }

        foodsCol.setCellFactory(column -> {
            return new TableCell<User, JFXButton>() {
                @Override
                protected void updateItem(JFXButton item, boolean empty) {
                    super.updateItem(item, empty);
                    try {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Button foodsButton = new Button("Foods");
                            foodsButton.setOnAction(event -> {
                                User selectedUser = getTableView().getItems().get(getIndex());
                                if (selectedUser != null) {
                                    openListOfFoodsPerUserWindow(selectedUser);
                                }
                            });
                            setGraphic(foodsButton);
                            setText(null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error in Foods Button Cell: " + e.getMessage());
                        errorAlert.showAndWait();
                    }
                }
            };
        });


        deleteUserCol.setCellFactory(column -> {
            return new TableCell<User, JFXButton>() {
                @Override
                protected void updateItem(JFXButton item, boolean empty) {
                    super.updateItem(item, empty);
                    try {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Button deleteButton = new Button("Delete");
                            deleteButton.setOnAction(event -> {
                                User selectedUser = getTableView().getItems().get(getIndex());
                                if (selectedUser != null) {
                                    deleteUser(selectedUser);
                                }
                            });
                            setGraphic(deleteButton);
                            setText(null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error in Delete Button Cell: " + e.getMessage());
                        errorAlert.showAndWait();
                    }
                }
            };
        });
    }

    /**
     *
     * @author: Amina
     */
    private void loadAdminReport(){
        refreshReport();
        AdminService adminService = new AdminService();
        String report = adminService.printFoodEntriesPerWeekComparison();
        reportText.setText(report);
        loadReportTable();
    }

    private void openListOfFoodsPerUserWindow(User selectedUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FoodListPerUser.fxml"));
            Parent parent = loader.load();
            FoodListPerUserController controller = loader.getController();
            controller.initializeData(selectedUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadReportTable() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        avgCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("avgCalories"));
        limitExceededColumn.setCellValueFactory(new PropertyValueFactory<>("hasExceededMoneyLimit"));

        List<User> tableRows = adminService.getReportForUsers();
        ObservableList<User> observableList = FXCollections.observableArrayList(tableRows);
        caloriesTable.setItems(observableList);
    }

    private void deleteUser(User selectedUser) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this user?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try {
                boolean success = userDAO.deleteUser(selectedUser.getUserId());
                if (success) {
                    usersTable.getItems().remove(selectedUser);
                    refreshTable();
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to delete the user.");
                    errorAlert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the user.");
                errorAlert.showAndWait();
            }
        }
    }

    @FXML
    private void refreshTable () {
        List<User> users = userDAO.getAllUsers();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(users);
        usersTable.setItems(userObservableList);
    }

    /**
     *
     * @author: Amina
     */
    @FXML
    private void refreshReport(){
        AdminService adminService = new AdminService();
        UserService userService=new UserService();
        userService.updateHasExceededLimitForAllUsers();
        List<User> reportList = adminService.getAvgCaloriesPerUserLast7Days();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(reportList);
        caloriesTable.setItems(userObservableList);
}
}
