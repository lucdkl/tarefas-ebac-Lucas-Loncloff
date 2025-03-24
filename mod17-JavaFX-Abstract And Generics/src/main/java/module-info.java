module com.vkl.mod17 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.vkl to javafx.fxml;
    exports com.vkl;
}