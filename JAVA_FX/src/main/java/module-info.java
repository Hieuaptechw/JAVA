module com.example.asignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.example.asignment to javafx.fxml;
    exports com.example.asignment;
    exports com.example.asignment.Controller;
    opens com.example.asignment.Controller to javafx.fxml;
    opens com.example.asignment.Entity to javafx.base;
}