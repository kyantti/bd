package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SelectTableController {

    @FXML
    void selectAddressesTable(ActionEvent event) {
        try {
            App.openNewWindow("addressesTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectProjectsTable(ActionEvent event) {
        try {
            App.openNewWindow("projectsTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectServicesTable(ActionEvent event) {
        try {
            App.openNewWindow("servicesTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectAscriptionsTable(ActionEvent event) {
        try {
            App.openNewWindow("ascriptionsTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectResourcesTable(ActionEvent event) {
        try {
            App.openNewWindow("resourcesTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectSectionsTable(ActionEvent event) {
        try {
            App.openNewWindow("sectionsTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
