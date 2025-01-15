package sistemas.biblioteca.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemas.biblioteca.App;


public class Login {

    @FXML
    private TextField user_field;
    @FXML
    private TextField pass_field;
    @FXML
    private Button login_button;
    @FXML
    private Button close_button;
    @FXML
    private Label registration_inicio_button;

    private Stage punteroStage = App.puntero1Stage;

    @FXML
    public void switchMenu() throws IOException {
        App.setRoot("secondary");
        punteroStage.sizeToScene();
        punteroStage.centerOnScreen();
    }    
}
