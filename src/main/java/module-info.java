module org.calc2.enviarmail {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.email;
    requires java.desktop;


    opens org.calc2.enviarmail to javafx.fxml;
    exports org.calc2.enviarmail;
}