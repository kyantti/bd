package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class SelectTableController implements Initializable {

    @FXML
    private Button queryOneButton;

    @FXML
    private Button queryThreeButton;

    @FXML
    private Button queryTwoButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip queryOneTooltip = new Tooltip(
                "Obtener la relación de los proyectos y subproyectos que no tienen adscritos personal técnico externo, ordenado alfabéticamente con el siguiente formato:\nNombre_corto_Proyecto, Nombre_largo_Subproyecto");
        Tooltip queryTwoTooltip = new Tooltip(
                "Obtener el servicio o servicios que ocupan el mayor número de recursos contabilizando conjuntamente los técnicos internos y los técnicos externos de empresas privadas con el siguiente formato:\nCódigo_de_servicio, Nombre_servicio, Número_de_recursos");
        Tooltip queryThreeTooltip = new Tooltip(
                "Obtener la relación de los proyectos y subproyectos que tienen asignados recursos técnicos externos de manera exclusiva, ordenado alfabéticamente con el formato:\nNombre_corto_Proyecto, Nombre_largo_Subproyecto");

        queryOneTooltip.setShowDelay(Duration.seconds(0.5d));
        queryTwoTooltip.setShowDelay(Duration.seconds(0.5d));
        queryThreeTooltip.setShowDelay(Duration.seconds(0.5d));

        queryOneButton.setTooltip(queryOneTooltip);
        queryTwoButton.setTooltip(queryTwoTooltip);
        queryThreeButton.setTooltip(queryThreeTooltip);

    }

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

    @FXML
    void selectQueryOne(ActionEvent event) {
        try {
            App.openNewWindow("projectSubprojectWithoutTechnicianTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectQueryTwo(ActionEvent event) {
        try {
            App.openNewWindow("serviceResourceTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectQueryThree(ActionEvent event) {
        try {
            App.openNewWindow("projectSubprojectWithTechnicianTable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
