package sistemas.biblioteca.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.reactivex.rxjava3.core.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.model.Usuario;

public class chatServiceImpl  implements CRUDService<Usuario>{

    @Override
    public Observable<Usuario> GET() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/users"))
                .build();
        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        var lista = objectMapper.readValue(response.body(), new TypeReference<List<Usuario>>(){});
        return Observable.fromIterable(lista);
    }

    @Override
    public void POST(Usuario usuario, VBox lista) {
            Label label = new Label(usuario.getNombre());
            ImageView image = new ImageView(getClass().getResource("/sistemas/biblioteca/media/menu_chat/user_icon.png").toString());
            image.setPreserveRatio(true);
            image.setFitHeight(31);
            image.setFitWidth(27);
            HBox a = new HBox();
            //Estilos prueba
            a.getStylesheets().add(getClass().getResource("/sistemas/biblioteca/css/chat.css").toExternalForm());
            a.getStyleClass().add("user_box"); //Para test
            
            a.setPrefHeight(20); 
            a.setAlignment(Pos.CENTER_LEFT);
            a.setPadding(new Insets(6, 10, 6, 20));
            a.setSpacing(8);
            a.setCursor(Cursor.HAND);
            a.getChildren().addAll(image,label);

            lista.getChildren().add(a);
        
    }
}
