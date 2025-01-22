package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sistemas.biblioteca.script.animaciones_predefinidas;

public class principal extends animaciones_predefinidas {

    /* Barra */
    @FXML
    ImageView user_icon, settings_icon, register_icon, books_icon, chat_icon, home_icon;
    
    /*Panel principal*/
    @FXML
    HBox slides_info;
    @FXML
    ImageView chat_button, books_button, weather_button;
    
    
    /* Animaciones */


    @FXML
    public void infocusanimated_booksButton() {
        infocus(books_button);
    }
    @FXML
    public void nofocusanimated_booksButton() {
        nofocus(books_button);
    }
    
    @FXML
    public void infocusanimated_weatherButton() {
        infocus(weather_button);
    }
    @FXML
    public void nofocusanimated_weatherButton() {
        nofocus(weather_button);
    }

    @FXML
    public void infocusanimated_slides() {
        infocus(slides_info);
    }

    @FXML
    public void nofocusanimated_slides() {
        nofocus(slides_info);
    }

    @FXML
    public void infocusanimated_chatButton() {
        infocus(chat_button);
    }

    @FXML
    public void nofocusanimated_chatButton() {
        nofocus(chat_button);
    }

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