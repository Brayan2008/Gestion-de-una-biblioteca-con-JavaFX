package sistemas.biblioteca.controllers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;

public class verLibros {

    @FXML
    void initialize() throws URISyntaxException, IOException{
        downloadResources("");
    }

    private void downloadResources(String urlString) {
        /* Estableciendo conexion */
        URL direcccion = new URI(urlString).toURL();
        HttpURLConnection con = (HttpURLConnection) direcccion.openConnection();
        /* Peticion */
        con.setRequestMethod("GET");
        /* Lectura */
        BufferedInputStream in = new BufferedInputStream(con.getInputStream());
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            System.out.write(buffer, 0, bytesRead);
        }
        in.close();
    }
    }
}