package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import sistemas.biblioteca.script.animaciones_predefinidas;

public class principal extends animaciones_predefinidas {

    @FXML
    ImageView user_icon;
    @FXML
    ImageView settings_icon;
    @FXML
    ImageView register_icon;
    @FXML
    ImageView books_icon;
    @FXML
    ImageView chat_icon;
    @FXML
    ImageView home_icon;

    @FXML
    public void infocusanimated_user() {
        infocus(user_icon);
    }

    @FXML
    public void nofocusanimated_user() {
        nofocus(user_icon);
    }

    @FXML
    public void infocusanimated_chat() {
        infocus(chat_icon);
    }

    @FXML
    public void nofocusanimated_chat() {
        nofocus(chat_icon);
    }

    @FXML
    public void infocusanimated_register() {
        infocus(register_icon);
    }

    @FXML
    public void nofocusanimated_register() {
        nofocus(register_icon);
    }

    @FXML
    public void infocusanimated_config() {
        infocus(settings_icon);
    }

    @FXML
    public void nofocusanimated_config() {
        nofocus(settings_icon);
    }

    @FXML
    public void infocusanimated_home() {
        infocus(home_icon);
    }

    @FXML
    public void nofocusanimated_home() {
        nofocus(home_icon);
    }

    @FXML
    public void infocusanimated_books() {
        infocus(books_icon);
    }

    @FXML
    public void nofocusanimated_books() {
        nofocus(books_icon);
    }

    @FXML
    public void switchPrestamo() {
        System.out.println("Entraste a prestamo");
    }

}