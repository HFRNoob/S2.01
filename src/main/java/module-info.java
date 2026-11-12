module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;

    exports application;
    exports application.controller;
    exports application.model;

    opens application to javafx.graphics, javafx.fxml;
    opens application.controller to javafx.fxml;
}
