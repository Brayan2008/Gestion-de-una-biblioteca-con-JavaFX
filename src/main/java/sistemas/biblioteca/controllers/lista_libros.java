package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import sistemas.biblioteca.services.librosServiceImpl;
import sistemas.biblioteca.services.interfaces.librosService;

public class lista_libros {

    librosService libro = new librosServiceImpl();

    @FXML
    FlowPane lista_libros;

    @FXML
    public void actualizar_libros() {
        lista_libros.getChildren().clear();
        libro.COLOCAR(lista_libros);
    }

    @FXML
    public void initialize() {
        libro.COLOCAR(lista_libros);
    }
    
}
