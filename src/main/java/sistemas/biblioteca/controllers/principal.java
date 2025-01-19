package sistemas.biblioteca.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sistemas.biblioteca.script.animaciones_predefinidas;

public class principal extends animaciones_predefinidas {

    @FXML
    VBox boton1;

    @FXML
    public void infocusanimated() {
        infocus(boton1);
    }

    @FXML
    public void nofocusanimated() {
        nofocus(boton1);
    }

    @FXML
    public void switchPrestamo() {
        System.out.println("Entraste a prestamo");
    }
    
}