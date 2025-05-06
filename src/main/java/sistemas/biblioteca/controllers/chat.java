package sistemas.biblioteca.controllers;

import io.reactivex.rxjava3.core.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.model.Usuario;
import sistemas.biblioteca.services.CRUDService;
import sistemas.biblioteca.services.chatServiceImpl;

public class chat {

    CRUDService<Usuario> service = new chatServiceImpl();
    Observable<Usuario> users ;
    boolean flag = false;
    
    @FXML
    VBox lista_contactos;
    
    @FXML
    ImageView refresh_icon;
    
    @FXML
    public void refresh_users() {
        try {
            service.GET();   
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error con la API");
            alert.setContentText("Error al cargar los usuarios");
            alert.show();
            return;
        }
        //Borramos y volvemos a agregar
        lista_contactos.getChildren().clear();
        initialize();
    }
    

    @FXML
    public void initialize() {
        try {
            users = service.GET();
            users.subscribe((user)-> service.POST(user, lista_contactos));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nError al cargar los usuarios");
        }
    }
}

