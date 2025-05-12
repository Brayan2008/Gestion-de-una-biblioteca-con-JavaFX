package sistemas.biblioteca.controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    public void initialize() throws InterruptedException, URISyntaxException {
    var nodo_padre = pantalla_principal.getParent();
    nodo_padre.setEffect(new GaussianBlur(10));
    nodo_padre.setDisable(true);

    Stage stage_load = pantallaCarga();

    Task<String> carga = new Task<String>() {
        @Override
        protected String call() throws Exception {
            vista1 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/inicio.fxml")).load();
            vista2 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/verLibros.fxml")).load();            
            vista3 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/chat.fxml")).load();
            vista4 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/verListaPrestados.fxml")).load();
            vista5 = new FXMLLoader(App.class.getResource("/sistemas/biblioteca/view/configuracion.fxml")).load();            
            Platform.runLater(() -> content_pane.getChildren().add(vista1));
            return null;
        }};
    
        carga.setOnFailed(e -> {
            Platform.runLater(() ->{
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setContentText("No se pudieron cargar todas las vistas");
                error.showAndWait();
            });
        });

        carga.setOnSucceeded(e -> {nodo_padre.setEffect(new GaussianBlur(0));
                                   nodo_padre.setDisable(false); 
                                   stage_load.close();});

        new Thread(carga).start();
        
    };
    
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

//#region Metodos
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

    public Stage pantallaCarga(){
        ImageView load_image = new ImageView(getClass().getResource("/sistemas/biblioteca/media/principal/loading.gif").toString());
        load_image.setFitWidth(100);
        load_image.setFitHeight(100);
        Label text_loading = new Label("Resolviendo y calculando ...");
        text_loading.setAlignment(Pos.CENTER);
        text_loading.setFont(new Font("Aptos",14));
        VBox loading = new VBox(load_image, text_loading);
        loading.setAlignment(Pos.CENTER);
        Stage stage_load = new Stage();
        Scene scene_loading = new Scene(loading);
        stage_load.initStyle(StageStyle.UNDECORATED);
        stage_load.setScene(scene_loading);
        stage_load.centerOnScreen();
        stage_load.show();
        return stage_load;
    }
//#endregion

}
