package es.unex.cum.bd.practicapareja.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/es/unex/cum/bd/practicapareja/view/login"), 640, 480);
        //scene.getStylesheets().add(getClass().getResource("/es/unex/cum/bd/practicapareja/view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void openNewWindow(String fxml) throws IOException {
        Stage newStage = new Stage();
        Scene newScene = new Scene(loadFXML("/es/unex/cum/bd/practicapareja/view/" + fxml), 640, 480);
        newStage.setScene(newScene);
        newStage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}