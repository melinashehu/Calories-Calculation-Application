package gui;
import dao.*;
import entity.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import validation.*;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button closeButton;

    private void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }


    public void handleRegister(){
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String validationMessage = InputValidator.isValidInput(name, email, password);

        if(validationMessage!= null){
            showError(validationMessage);
            return;
        }

        User user = new User(0, name, email, password, "user");
        UserDAOImplementation userDao = new UserDAOImplementation();
        boolean success = userDao.addUser(user);
        if(success){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration successful!");
            alert.showAndWait();
        }else{
            showError("Registration failed. Check you credentials.");
        }
    }

    public void handleRegisterButtonMouseEntered(){
        registerButton.setStyle("-fx-background-color: #6f9973; -fx-text-fill: white; -fx-background-radius:10;");
    }
    public void handleRegisterButtonMouseExited(){
        registerButton.setStyle("-fx-background-color: #85b48a; -fx-text-fill:white; -fx-background-radius:10;");
    }

    public void handleRegisterCloseButtonMouseEntered(){
        closeButton.setStyle("-fx-background-color: #e6e6e6; -fx-text-fill: #666666; -fx-background-radius: 10;");
    }
    public void handleRegisterCloseButtonMouseExited(){
        closeButton.setStyle("-fx-background-color: #f2f2f2; -fx-text-fill: #999999; -fx-background-radius: 10;");
    }

    public void redirectToLoginForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Scene registerScene = new Scene(loader.load());
        Stage stage = (Stage)registerButton.getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }

    public void closeRegisterApp(){
        System.exit(0);
    }
}
