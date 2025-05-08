package sistemas.biblioteca.services;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import sistemas.biblioteca.model.Libros;
import sistemas.biblioteca.script.animaciones_predefinidas;
import sistemas.biblioteca.services.interfaces.librosService;

public class librosServiceImpl implements librosService, animaciones_predefinidas {

    BehaviorSubject<Libros> libros = BehaviorSubject.create();
 

    @Override
    public List<Libros> GET() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/books")).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            var lista =objectMapper.readValue(response.body(),new TypeReference<ArrayList<Libros>> (){});        
            Logger.getLogger(this.getClass().getName()).info("OK 200. Se obtuvo el json");
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void COLOCAR(Pane lista) {
        //Obtenemos el directorio de las imagenes temporales, en caso no exista lo creamos
        Path temp = Paths.get(System.getProperty("user.dir") + "/temp/images");
        File dir = new File(temp.toString());
        if (!dir.exists()) dir.mkdirs();
        //Ponemos las imagenes
        try {
            Observable<Libros> libros_list = Observable.fromIterable(GET());
            libros.subscribe(libro->ADD(libro, lista));
            libros_list.subscribe(libros::onNext);
        } catch (NullPointerException e) {
                Platform.runLater(() -> {Alert alert = new Alert(AlertType.ERROR);
                                               alert.setTitle("Error con la API");
                                               alert.setContentText("NO SE PUDO OBTENER LA INFORMACION DE LOS LIBROS");
                                               alert.show();});
                Logger.getLogger(this.getClass().getName()).severe("Error al obtener los datos de los libros");
                e.printStackTrace();
        }        

    }
       
    
    @Override
    public void ADD(Libros libro, Pane lista) {
            
            //Creamos el nodo Imagen 
            ImageView image = new ImageView();
            image.setFitHeight(83);
            image.setFitWidth(94);
            image.setPreserveRatio(true);
            
            //Creamos el Panel VBOX
            VBox b = new VBox();
            b.setAlignment(Pos.TOP_CENTER);
            b.setPadding(new Insets(10,5,10,5));
            b.setSpacing(5);
            b.setPrefSize(129, 180);
            //Animaciones            
            b.setOnMouseEntered(e -> this.infocus(b));
            b.setOnMouseExited(e -> this.nofocus(b));
            b.getStylesheets().add(getClass().getResource("/sistemas/biblioteca/css/chat.css").toExternalForm());
            b.getStyleClass().add("user_box"); //Para test

            //Creamos un archivo .png vacio
            Path ima_path = Paths.get(System.getProperty("user.dir")+"/temp/images/"+ libro.getImage_path());
            try {
                if(Files.notExists(ima_path)) Files.createFile(ima_path);
                Files.setAttribute(ima_path, "dos:hidden", true); //Ocultamos la imagen
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).severe("No se pudo crear el archivo: ");
                e.printStackTrace();
            }
            
            //Descargamos la imagen
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/" + libro.getImage_path())).build();
            try {
                HttpResponse<byte[]> response2 = client.send(request2, HttpResponse.BodyHandlers.ofByteArray());
                var bytes_imagen = response2.body();
                Files.write(ima_path,bytes_imagen); //Copiamos los bytes al archivo
                Logger.getLogger(this.getClass().getName()).info("Se descargo el archivo " + ima_path);
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).severe("No se pudo copiar el archivo ");
                e.printStackTrace();
            }
            
            //Ponemos la imagen
            image.setImage(new Image(ima_path.toUri().toString()));
            Label titulo = new Label(libro.getNombre_libro());
            titulo.setWrapText(true);
            titulo.setTextAlignment(TextAlignment.CENTER);
            Button button = new Button("Ver libro");
            button.setCursor(Cursor.HAND);
            button.setOnMouseClicked(e -> System.out.println("Aun por definir " + e));
            
            //Personalizacion
            button.getStylesheets().add(getClass().getResource("/sistemas/biblioteca/css/styles.css").toExternalForm());
            button.getStyleClass().add("greenbutton");
            button.getStyleClass().add("color_fondo01");

            b.getChildren().addAll(image,titulo,button);//Ponemos todo al VBOX
            lista.getChildren().add(b); //Ponemos el VBOX a la lista
        } 

}
