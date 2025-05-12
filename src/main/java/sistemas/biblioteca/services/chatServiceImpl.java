package sistemas.biblioteca.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sistemas.biblioteca.model.Usuario;
import sistemas.biblioteca.services.interfaces.chatService;

public class chatServiceImpl implements chatService{
    
    
    @Override
    public List<Usuario> GET(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/users"))
        .build();
        try {            
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.readValue(response.body(), new TypeReference<List<Usuario>>(){}); 
        } catch (Exception e) {
            return null; 
        }
    }
    
    @Override
    public void COLOCAR(Pane panel) {
        try {
            PublishSubject<Usuario> users = PublishSubject.create(); 
            Observable<Usuario> users_list = Observable.fromIterable(GET());
            users.subscribe(usuario->ADD(usuario, panel));
            users_list.subscribe(users::onNext);
            } catch (NullPointerException e) {
                Platform.runLater(() -> {Alert alert = new Alert(AlertType.ERROR);
                                               alert.setTitle("Error con la API");
                                               alert.setContentText("NO SE PUDO OBTENER LA INFORMACION DE LOS USUARIOS");
                                               alert.showAndWait();});
                Logger.getLogger(this.getClass().getName()).severe("Error al obtener los datos ");
                e.printStackTrace();
        }        
    }

    @Override
    public void ADD(Usuario usuario, Pane panel) {
        //Etiquetas (Imagenes aun no soportadas, todas por dafault)
        Label label = new Label(usuario.getNombre());
        ImageView image = new ImageView(getClass().getResource("/sistemas/biblioteca/media/menu_chat/user_icon.png").toString());
        image.setPreserveRatio(true);
        image.setFitHeight(31);
        image.setFitWidth(27);
        //Creacion de las HBOX
        HBox a = new HBox();
        a.getStylesheets().add(getClass().getResource("/sistemas/biblioteca/css/chat.css").toExternalForm());
        a.getStyleClass().add("user_box"); //Para test
        a.setPrefHeight(20); 
        a.setAlignment(Pos.CENTER_LEFT);
        a.setPadding(new Insets(6, 10, 6, 20));
        a.setSpacing(8);
        a.setCursor(Cursor.HAND);
        a.getChildren().addAll(image,label);
        //Añadimos al panel
        panel.getChildren().add(a);
    }
}
