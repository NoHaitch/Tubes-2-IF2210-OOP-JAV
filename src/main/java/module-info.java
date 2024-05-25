module javva.tubes2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.yaml.snakeyaml;

    opens javva.tubes2 to javafx.fxml;
    exports javva.tubes2;
    exports javva.tubes2.Card;
    exports javva.tubes2.Player;
    exports javva.tubes2.Card;
    exports javva.tubes2.dataLoader to com.fasterxml.jackson.databind;
    exports plugin;
}
