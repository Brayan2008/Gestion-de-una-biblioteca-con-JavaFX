module sistemas.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;

    opens sistemas.biblioteca to javafx.fxml;
    exports sistemas.biblioteca;
}
