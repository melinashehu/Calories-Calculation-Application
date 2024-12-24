package gui;
import dao.*;
import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import validation.*;

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public void handleRegister(){
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if(!InputValidator.isValidUserName(name)){
            showError("Name must be at least 3 characters long.");
            return;
        }
        if(!InputValidator.isValidEmail(email)){
            showError("Email must be a valid email format. \n example@domain.com");
            return;
        }
        if(!InputValidator.isValidPassword(password)){
            showError("Password must be at least 6 characters long.");
            return;
        }

        User user = new User(0, name, email, password, "user");
        UserDAOImplementation userDao = new UserDAOImplementation();
        boolean success = userDao.addUser(user);
        if(success){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration successful!");
            alert.showAndWait();
        }else{
            showError("Registration faled. Check you credentials.");
        }
    }
}
