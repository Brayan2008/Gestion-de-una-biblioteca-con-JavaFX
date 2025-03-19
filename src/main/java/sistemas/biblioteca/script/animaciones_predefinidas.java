package sistemas.biblioteca.script;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public interface animaciones_predefinidas {
    
    default public void nofocus(Node a) {
        ScaleTransition transition = new ScaleTransition(Duration.seconds(0.2));
        transition.setNode(a);
        transition.setFromX(1.1);
        transition.setFromY(1.1);
        transition.setToX(0.9);
        transition.setToY(0.9);
        transition.play();
    }
    
    default void infocus(Node a) {
        ScaleTransition transition = new ScaleTransition(Duration.seconds(0.2));
        transition.setNode(a);
        transition.setFromX(0.9);
        transition.setFromY(0.9);
        transition.setToX(1.1);
        transition.setToY(1.1);
        transition.setAutoReverse(true);
        transition.play();
    }

}
