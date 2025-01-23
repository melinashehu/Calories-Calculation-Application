package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Start extends Application { 

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Register");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LoginView.fxml"))));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
