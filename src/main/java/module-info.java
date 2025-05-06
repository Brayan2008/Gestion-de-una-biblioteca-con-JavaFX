module sistemas.biblioteca {
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires io.reactivex.rxjava3;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.logging;
    requires transitive javafx.graphics;

    opens sistemas.biblioteca.controllers to javafx.fxml;
    exports sistemas.biblioteca.model;
    exports sistemas.biblioteca;
}
