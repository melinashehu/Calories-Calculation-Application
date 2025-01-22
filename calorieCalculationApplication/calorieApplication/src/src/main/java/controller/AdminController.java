package controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
    private Button logOutButton;
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadUserData();
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
            List<User> users = userService.getAllUsersService();
            if (users.isEmpty()) {
                System.out.println("There are no users.");
            }
            ObservableList<User> userObservableList = FXCollections.observableArrayList(users);
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

    private void deleteUser(User selectedUser) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this user?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try {
                boolean success = userService.deleteUserService(selectedUser.getUserId());
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
    private void refreshTable() {
        List<User> users = userService.getAllUsersService();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(users);
        usersTable.setItems(userObservableList);
    }

    @FXML
    private void handleShowReportButton(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AdminReport.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Report");
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void handleLogOutButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginView.fxml"));
        Scene registerScene = new Scene(loader.load());
        Stage stage = (Stage)logOutButton.getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }
}
