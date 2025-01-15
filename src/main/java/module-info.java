module sistemas.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens sistemas.biblioteca.controllers to javafx.fxml;
    exports sistemas.biblioteca;
}
