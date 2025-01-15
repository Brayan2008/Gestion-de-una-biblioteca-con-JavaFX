package sistemas.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Stage puntero1Stage;
    private static Scene scene;

    public static void escena(String fxml) throws IOException {
        scene = new Scene(loadFXML(fxml));
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Inicie la aplicacion");
    } 

    @Override
    public void start(Stage stage1) throws IOException {
        puntero1Stage = stage1;
        escena("Login");    
        stage1.sizeToScene();
        stage1.setTitle("Sistema de biblioteca");
        stage1.centerOnScreen();
        stage1.setResizable(false);
        stage1.setScene(scene);
        stage1.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
   
    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Detuve la aplicacion");
    }

}