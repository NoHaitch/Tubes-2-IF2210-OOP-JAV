module javva.tubes2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens javva.tubes2 to javafx.fxml;
    exports javva.tubes2;
}