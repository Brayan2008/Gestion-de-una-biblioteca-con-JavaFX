package sistemas.biblioteca.services;

import java.io.IOException;
import java.net.URISyntaxException;

import io.reactivex.rxjava3.core.Observable;
import javafx.scene.layout.VBox;

public interface CRUDService<T>{
    Observable<T> GET() throws URISyntaxException, IOException, InterruptedException;
    public void POST(T user, VBox lista);
}
