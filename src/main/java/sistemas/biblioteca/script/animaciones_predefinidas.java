package sistemas.biblioteca.script;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;

public abstract class animaciones_predefinidas {
    
    

    public void nofocus(Node a) {
        ScaleTransition transition = new ScaleTransition();
        transition.setNode(a);
        transition.setFromX(1.1);
        transition.setFromY(1.1);
        transition.setToX(1);
        transition.setToY(1);
        transition.play();
    }
    
    public void infocus(Node a) {
        ScaleTransition transition = new ScaleTransition();
        transition.setNode(a);
        transition.setFromX(1);
        transition.setFromY(1);
        transition.setToX(1.1);
        transition.setToY(1.1);
        transition.setAutoReverse(true);
        transition.play();
    }


}
