package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import sistemas.biblioteca.services.librosServiceImpl;
import sistemas.biblioteca.services.interfaces.librosService;

public class lista_libros {

    @FXML
    FlowPane lista_libros;

    librosService libro = new librosServiceImpl();

    @FXML
    public void initialize() {
        libro.COLOCAR(lista_libros);
    }
    
}
