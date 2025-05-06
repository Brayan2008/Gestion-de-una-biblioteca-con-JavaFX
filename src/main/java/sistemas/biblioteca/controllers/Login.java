package sistemas.biblioteca.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemas.biblioteca.App;
import sistemas.biblioteca.script.animaciones_predefinidas;


public class Login  implements animaciones_predefinidas{

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

    protected static Stage punteroStage = App.puntero1Stage;

    @FXML
    public void switchMenu() throws IOException {
        App.setRoot("principal");
        punteroStage.sizeToScene();
        punteroStage.setResizable(true);
        punteroStage.centerOnScreen();
    }

    //#region Animaciones
    @FXML
    public void infocusanimated() {
        infocus(login_button);    
    }
    
    @FXML
    public void nofocusanimated() {
        nofocus(login_button);    
    }
    //#endregion

}
