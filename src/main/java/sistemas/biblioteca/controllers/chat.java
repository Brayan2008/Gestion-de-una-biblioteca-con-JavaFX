package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.services.chatServiceImpl;
import sistemas.biblioteca.services.interfaces.chatService;

public class chat {

    @FXML
    VBox lista_contactos;

    @FXML
    ImageView refresh_icon;
    
    chatService service = new chatServiceImpl();
    
    @FXML
    public void refresh_users() {
        lista_contactos.getChildren().clear();
        initialize();
    }
    
    @FXML
    public void initialize() {
        service.COLOCAR(lista_contactos);
    }
}

