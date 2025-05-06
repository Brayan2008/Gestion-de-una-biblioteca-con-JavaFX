package sistemas.biblioteca.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.model.Libros;

public class lista_libros {

    @FXML
    VBox lista_libros;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/books")).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ArrayList<Libros> libros = objectMapper.readValue(response.body(),new TypeReference<ArrayList<Libros>> (){});        
        BehaviorSubject<List<Libros>> observable = BehaviorSubject.createDefault(libros);
        //Valores para FlowPane
        FlowPane a = new FlowPane();
        a.setHgap(12);
        a.setVgap(12);
        a.setPadding(new Insets(12, 20, 0, 20));
        lista_libros.getChildren().add(a);
        //Crear un directorio de imagenes temporales
        Path temp = Paths.get(System.getProperty("user.dir") + "/temp/images");
        File dir = new File(temp.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //Valores para la imagen
        observable.subscribe(librosList -> {
            for (Libros libro : librosList) {
                //Creamos la imagen 
                ImageView image = new ImageView();
                image.setFitHeight(83);
                image.setFitWidth(94);
                image.setPreserveRatio(true);
                //Creamos la imagen
                Path ima_path = Paths.get(System.getProperty("user.dir")+"/temp/images/"+ libro.getImage_path());
                if(Files.notExists(ima_path)) Files.createFile(ima_path);
                Files.setAttribute(ima_path, "dos:hidden", true);
                //Descargamos la imagen
                HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/" + libro.getImage_path())).build();
                HttpResponse<byte[]> response2 = client.send(request2, HttpResponse.BodyHandlers.ofByteArray());
                var c = response2.body();
                Files.write(ima_path,c);
                //Valores para los VBOX
                VBox b = new VBox();
                b.setAlignment(Pos.CENTER);
                b.setPadding(new Insets(10,0,10,0));
                b.setSpacing(5);
                b.setPrefSize(129, 223);
                System.out.println("Se descargo la imagen!!");
                //Ponemos la imagen
                image.setImage(new Image(ima_path.toUri().toString()));
                Label titulo = new Label(libro.getNombre_libro());
                Button button = new Button("Ver libro");
                b.getChildren().addAll(image,titulo,button);
                a.getChildren().add(b);
            }
        });

        
        
    
    }
    
}
