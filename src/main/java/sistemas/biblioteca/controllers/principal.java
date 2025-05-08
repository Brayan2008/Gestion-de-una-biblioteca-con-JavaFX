package sistemas.biblioteca.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.App;
import sistemas.biblioteca.script.animaciones_predefinidas;
import sistemas.biblioteca.services.chatServiceImpl;
import sistemas.biblioteca.services.interfaces.chatService;

public class principal implements animaciones_predefinidas{

    chatService service = new chatServiceImpl();


    /* Barra */
    @FXML
    ImageView user_icon,settings_icon, register_icon, books_icon, chat_icon, home_icon;
    
    @FXML
    MenuButton user_icon_button;

    @FXML
    MenuItem user_configuration, logout;

    @FXML
    VBox content_pane,pantalla_principal;

    @FXML
    ScrollPane scroll_pane;
    
    Node vista1, vista2, vista3, vista4,vista5;
    
    //#region Initialization
    /**
     * Metodo que se ejecuta al iniciar la aplicacion
     * <p> Carga las vistas de inicio y verLibros </p>
     * @throws InterruptedException en caso de que se interrumpan los hilos
     */
    @FXML
    public void initialize() throws InterruptedException {
        Thread hilo = new Thread(()->{
            try {
                vista1 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/inicio.fxml")).load();
                vista3 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/chat.fxml")).load();
                vista5 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/configuracion.fxml")).load();
                System.out.println("Se precargo al vista 1 y 3 ");
            } catch (IOException e) {   
            Logger.getLogger(this.getClass().getName()).severe("Error al cargar las vistas 1, 3 o 5");
            e.printStackTrace();
        }
    });
    Thread hilo2 = new Thread(()->{
        try {
            vista2 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/verLibros.fxml")).load();
            vista4 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/verListaPrestados.fxml")).load();
            System.out.println("Se precargo al vista 2");
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).severe("Error al cargar las vistas 2 o 4 "); 
            e.printStackTrace();
            }
        });         
        hilo.start();
        hilo.join();
        hilo2.start();
        hilo2.join();
        content_pane.getChildren().add(vista1);
    }
    //#endregion

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
        agregarScroll();
        content_pane.getChildren().clear();
        content_pane.getChildren().add(vista2);
    }
    
    @FXML
    public void switchHome() throws IOException {
        agregarScroll();
        content_pane.getChildren().clear();
        content_pane.getChildren().add(vista1);
    }

    @FXML
    public void switchChat(){
        try {
            service.GET();   
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error con la API");
            alert.setContentText("Error al cargar los usuarios");
            alert.show();
            return;
        } finally{
            var a = pantalla_principal.getChildren();
            a.set(a.size()-1, vista3);
            VBox.setVgrow(vista3, Priority.ALWAYS);
        }
    }

    @FXML
    public void switchListBooks() {
        agregarScroll();
        content_pane.getChildren().clear();
        content_pane.getChildren().add(vista4);
    }
    
    @FXML
    public void switchConfiguracion() {
        agregarScroll();
        content_pane.getChildren().clear();
        content_pane.getChildren().add(vista5);    
    }

    @FXML
    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cerrar sesion");
        alert.setHeaderText("¿Estas seguro de cerrar sesion?");
        alert.showAndWait();
        ButtonType result = alert.getResult();
        if (result == ButtonType.OK) {
            try {
                App.setRoot("Login");
                App.puntero1Stage.setResizable(false);
                App.puntero1Stage.setHeight(280);
                App.puntero1Stage.setWidth(508);
                App.puntero1Stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert.close();
        }
    }


//#endregion
    
    public void agregarScroll() {
        scroll_pane = new ScrollPane(content_pane);
    
        // Hacer que el ScrollPane se ajuste al tamaño disponible
        scroll_pane.setFitToWidth(true);
        scroll_pane.setFitToHeight(true);

        // Configurar el crecimiento del ScrollPane dentro del VBox
        VBox.setVgrow(scroll_pane, Priority.ALWAYS);
        
        //Agregamos el scrollpane
        var a = pantalla_principal.getChildren(); //
        a.set(a.size()-1, scroll_pane);
    }

}
