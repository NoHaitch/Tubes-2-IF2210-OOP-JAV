module javva.tubes2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;

    exports javva.tubes2;
    exports javva.tubes2.dataLoader to com.fasterxml.jackson.databind;
    exports plugin;
}