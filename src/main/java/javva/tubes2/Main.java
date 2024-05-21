package javva.tubes2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 920);
        stage.setTitle("KneeGrow CardGame");
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(920);
        stage.setMinWidth(1250);
    }

    public static void main(String[] args) {
        launch();
    }
}