module com.jhorn384 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.jhorn384 to javafx.fxml;

    exports com.jhorn384;
}
