module com.jhorn384 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.jhorn384 to javafx.fxml;
    exports com.jhorn384;
}
