package sistemas.biblioteca.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.App;
import sistemas.biblioteca.script.animaciones_predefinidas;

public class principal implements animaciones_predefinidas{

    /* Barra */
    @FXML
    ImageView user_icon, settings_icon, register_icon, books_icon, chat_icon, home_icon;
    
    @FXML
    VBox content_pane;
    
    Node vista1, vista2, vista3, vista4;
    
    /**
     * Metodo que se ejecuta al iniciar la aplicacion
     * <p> Carga las vistas de inicio y verLibros </p>
     * @throws InterruptedException en caso de que se interrumpan los hilos
     */
    @FXML
    public void initialize() throws InterruptedException {
        Thread hilo = new Thread(()->{
            try {
                vista1 = new FXMLLoader(App.class.getResource("inicio.fxml")).load();
                System.out.println("Se precargo al vista 1");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("\nError al cargar la vista 1"); 
            }
        });
        Thread hilo2 = new Thread(()->{
            try {
                vista2 = new FXMLLoader(App.class.getResource("verLibros.fxml")).load();
                System.out.println("Se precargo al vista 2");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("\nError al cargar la vista 2"); 
            }
        });         
        hilo.start();
        hilo.join();
        hilo2.start();
        hilo2.join();
        content_pane.getChildren().add(vista1);
    }
    
    //#region Animaciones  
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
        content_pane.getChildren().clear();
        content_pane.getChildren().add(vista2);
    }
    
    @FXML
    public void switchHome() throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(vista1);
        
    }
    //#endregion
    


}
