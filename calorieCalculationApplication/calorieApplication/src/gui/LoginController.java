package gui;

import javafx.fxml.FXML;
import dao.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import validation.*;
import entity.*;
public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label messageLabel;
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
    public void handleLogin(){
        String email = emailField.getText();
        String password = passwordField.getText();
        if(!InputValidator.isValidEmail(email)){
            showError("Email is invalid.");
            return;
        }
        if(InputValidator.isEmpty(password)){
            showError("Password is invalid.");
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


}
