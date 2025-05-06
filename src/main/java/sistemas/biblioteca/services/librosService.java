package sistemas.biblioteca.services;

import io.reactivex.rxjava3.core.Observable;
import sistemas.biblioteca.model.Libros;

public interface librosService {
    Observable<Libros> getLibros();
    public void agregar(Libros libro);
}
