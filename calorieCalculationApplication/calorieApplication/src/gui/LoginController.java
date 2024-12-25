package gui;

import javafx.fxml.FXML;
import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import validation.*;
import entity.*;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button closeButton;


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public void handleLogin(){
        String email = emailField.getText();
        String password = passwordField.getText();
        String validationMessage = InputValidator.isValidInput(email, password);

        if(validationMessage!= null){
            showError(validationMessage);
            return;
        }

        UserDAOImplementation userDao = new UserDAOImplementation();
        User user = userDao.getUserByEmailAndPassword(email, password);
        if(user != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Log-in successful! Welcome, "+user.getUserName());
            alert.showAndWait();
        }else{
            showError("Invalid email or password.");
        }
    }

    public void redirectToRegisterForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterView.fxml"));
        Scene registerScene = new Scene(loader.load());
        Stage stage = (Stage)loginButton.getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }

    public void handleLoginButtonMouseEntered(){
        loginButton.setStyle("-fx-background-color: #6f9973; -fx-text-fill: white; -fx-background-radius:10;");
    }
    public void handleLoginButtonMouseExited(){
        loginButton.setStyle("-fx-background-color: #85b48a; -fx-text-fill:white; -fx-background-radius:10;");
    }

    public void handleLoginCloseButtonMouseEntered(){
        closeButton.setStyle("-fx-background-color: #e6e6e6; -fx-text-fill: #666666; -fx-background-radius: 10;");
    }
    public void handleLoginCloseButtonMouseExited(){
        closeButton.setStyle("-fx-background-color: #f2f2f2; -fx-text-fill: #999999; -fx-background-radius: 10;");
    }

    public void closeLoginApp(){
        System.exit(0);
    }
}
