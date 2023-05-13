package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;
import java.sql.SQLException;

import es.unex.cum.bd.practicapareja.model.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/es/unex/cum/bd/practicapareja/view/login"));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(event -> {
            if (Database.getConnection() != null) {
                try {
                    Database.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
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
        Scene newScene = new Scene(loadFXML("/es/unex/cum/bd/practicapareja/view/" + fxml));
        switch (fxml) {
            case "addressesTable":
                newStage.setTitle("Direcciones");
                break;
            case "ascriptionsTable":
                newStage.setTitle("Adscripciones");
                break;
            case "projectsTable":
                newStage.setTitle("Proyectos");
                break;
            case "resourcesTable":
                newStage.setTitle("Recursos");
                break;
            case "sectionsTable":
                newStage.setTitle("Secciones");
                break;
            case "servicesTable":
                newStage.setTitle("Servicios");
                break;
            default:
                break;
        }
        newStage.setScene(newScene);
        newStage.sizeToScene();
        newStage.setResizable(false);
        newStage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}