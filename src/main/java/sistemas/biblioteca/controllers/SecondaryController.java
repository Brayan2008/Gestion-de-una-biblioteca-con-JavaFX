package sistemas.biblioteca.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import sistemas.biblioteca.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}