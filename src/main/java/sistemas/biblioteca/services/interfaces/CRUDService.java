package sistemas.biblioteca.services.interfaces;

import java.util.List;

import javafx.scene.layout.Pane;

public interface CRUDService<T>{


    /**
     * Realiza una peticion HTTP al backend para obtener un JSON. Luego
     * se deserializa y lo convierte en un generico T
     * @return Devuelve una lista de dicho generico
      */
    List<T> GET();

    /**
     * Coloca en la GUI los nodos procesados.
    */
    void COLOCAR(Pane panel);

    void ADD(T t, Pane panel);

}
    