package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectTableController {

    private void openNewWindow(String view) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/unex/cum/bd/practicapareja/view/" + view + ".fxml"));	
		try {
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectAddressesTable(ActionEvent event) {
        openNewWindow("addressesTable");
    }

    @FXML
    void selectProjectsTable(ActionEvent event) {
        openNewWindow("projectsTable");
    }

    @FXML
    void selectServicesTable(ActionEvent event) {
        openNewWindow("servicesTable");
    }

}
