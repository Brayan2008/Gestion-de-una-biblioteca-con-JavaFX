package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sistemas.biblioteca.script.animaciones_predefinidas;

public class inicio implements animaciones_predefinidas {
    @FXML
    HBox slides_info;
    
    @FXML
    ImageView chat_button, books_button, weather_button;
    
    //#region Animaciones
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
    //#endregion
    
    //#region Switches
    
    //#endregion

}
