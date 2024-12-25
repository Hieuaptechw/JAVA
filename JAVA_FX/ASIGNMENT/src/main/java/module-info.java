module com.example.asignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.asignment to javafx.fxml;
    exports com.example.asignment;
    exports com.example.asignment.Controller;
    opens com.example.asignment.Controller to javafx.fxml;
}