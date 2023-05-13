package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.AscriptionDao;
import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.entities.Ascription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AscriptionsTableController implements Initializable{

    @FXML
    private TableView<Ascription> ascriptionTableView;

    @FXML
    private TableColumn<Ascription, String> dateCol;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Ascription, String> resourceIdCol;

    @FXML
    private TextField resourceIdTextField;

    @FXML
    private TableColumn<Ascription, String> sectionIdCol;

    @FXML
    private TextField sectionIdTextField;

    private AscriptionDao ascriptionDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        ascriptionDao = daoManager.getAscriptionDao();
        try {
            ObservableList<Ascription> addresses = FXCollections.observableArrayList(ascriptionDao.getAll());
            sectionIdCol.setCellValueFactory(new PropertyValueFactory<>("sectionId"));
            resourceIdCol.setCellValueFactory(new PropertyValueFactory<>("resourceId"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("ascriptionDate"));
            ascriptionTableView.setItems(addresses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ascriptionTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Obtener la fila en la que se hizo clic
                Ascription selectedAscription = ascriptionTableView.getSelectionModel().getSelectedItem();
        
                if (selectedAscription != null) {
                    // La fila se ha seleccionado, puedes hacer algo con la información
                    sectionIdTextField.setText(String.valueOf(selectedAscription.getSectionId()));
                    resourceIdTextField.setText(String.valueOf(selectedAscription.getResourceId()));
                    datePicker.setValue(selectedAscription.getAscriptionDate());
                }
            }
            else if (event.getClickCount() == 2) {
                ascriptionTableView.getSelectionModel().clearSelection();
                // La fila no se ha seleccionado, borra los campos de texto
                clearAllTextFields();
            }
        });
    }

    private void clearAllTextFields() {
        sectionIdTextField.clear();
        resourceIdTextField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


    @FXML
    void addOrUpdate(ActionEvent event) {
        Ascription selectedAscription = ascriptionTableView.getSelectionModel().getSelectedItem();
        
        int sectionId = Integer.parseInt(sectionIdTextField.getText());
        int resourceId = Integer.parseInt(resourceIdTextField.getText());
        LocalDate date = datePicker.getValue();

        if (selectedAscription != null) {
            //UPDATE
            try {
                ascriptionDao.update(new Ascription(sectionId, resourceId, date));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        else{
            //INSERT
            try {
                ascriptionDao.insert(new Ascription(sectionId, resourceId, date));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        try {
            ObservableList<Ascription> ascriptions = FXCollections.observableArrayList(ascriptionDao.getAll());
            ascriptionTableView.setItems(ascriptions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        clearAllTextFields();
    }

    @FXML
    void delete(ActionEvent event) {
        Ascription selectedAscription = ascriptionTableView.getSelectionModel().getSelectedItem();
        try {
            ascriptionDao.delete(selectedAscription);
            ObservableList<Ascription> projects = FXCollections.observableArrayList(ascriptionDao.getAll());
            ascriptionTableView.setItems(projects);
        } catch (SQLException e) {
            showAlert(e);
        }

        clearAllTextFields();
    }
    
}
