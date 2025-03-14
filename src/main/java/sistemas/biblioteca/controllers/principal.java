package sistemas.biblioteca.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.App;
import sistemas.biblioteca.script.animaciones_predefinidas;

public class principal extends animaciones_predefinidas {

    /* Barra */
    @FXML
    ImageView user_icon, settings_icon, register_icon, books_icon, chat_icon, home_icon;
    
    @FXML
    ScrollPane content_pane;
    /*Panel principal*/
    
    @FXML
    HBox slides_info;
    
    @FXML
    ImageView chat_button, books_button, weather_button;
    
    @FXML
    VBox inicio_box;

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
    //#endregion

    //#region Switches
    @FXML
    public void switchPrestamo() throws IOException {
        if (content_pane.getContent()==cache) {
            return;
        }
        cache = new FXMLLoader(App.class.getResource("chat.fxml")).load();
        content_pane.getContent().setVisible(false);
        content_pane.setContent(cache);
        System.out.println(inicio_box==null);
    }

    private static Node cache;

    @FXML
    public void switchHome() {
        if (content_pane.getContent()==inicio_box) {
            System.out.println("Ya se encuentra aqui");
            return;
        }
        content_pane.getChildrenUnmodifiable().removeAll(cache);
        inicio_box.setVisible(true);
        content_pane.setContent(inicio_box);
    }
    //#endregion

}