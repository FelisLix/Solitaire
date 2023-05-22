package solitaire.solitaire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SolitaireApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SolitaireApplication.class.getResource("Solitaire.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 500);
        stage.setTitle("Solitaire");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}