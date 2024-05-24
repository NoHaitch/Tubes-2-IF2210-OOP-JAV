module javva.tubes2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;

    exports javva.tubes2.Player;
    exports javva.tubes2.Card;
    exports javva.tubes2.dataLoader to com.fasterxml.jackson.databind;
    exports plugin;
}
