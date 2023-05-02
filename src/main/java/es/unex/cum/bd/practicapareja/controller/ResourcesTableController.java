package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.ResourceDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.entities.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResourcesTableController implements Initializable {

    @FXML
    private TableColumn<Resource, String> idCol;

    @FXML
    private TableColumn<Resource, String> nameCol;

    @FXML
    private TextField nameTextField;

    @FXML
    private TableColumn<Resource, String> nrptCol;

    @FXML
    private TextField nrptTextField;

    @FXML
    private TableView<Resource> resourceTableView;

    @FXML
    private TableColumn<Resource, String> sectionCol;

    @FXML
    private TextField sectionTextField;

    @FXML
    private TextField idTextField;

    private ResourceDao resourceDao;

    private void showAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    private void clearAllTextFields(){
        idTextField.clear();
        nameTextField.clear();
        sectionTextField.clear();
        nrptTextField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        resourceDao = daoManager.getResourceDao();
        try {
            ObservableList<Resource> resources = FXCollections.observableArrayList(resourceDao.getAll());
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            sectionCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            nrptCol.setCellValueFactory(new PropertyValueFactory<>("nrpt"));
            resourceTableView.setItems(resources);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resourceTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Obtener la fila en la que se hizo clic
                Resource selectedresource = resourceTableView.getSelectionModel().getSelectedItem();
        
                if (selectedresource != null) {
                    // La fila se ha seleccionado, puedes hacer algo con la información
                    idTextField.setText(String.valueOf(selectedresource.getId()));
                    nameTextField.setText(selectedresource.getName());
                    sectionTextField.setText(String.valueOf(selectedresource.getSectionId()));
                    nrptTextField.setText(String.valueOf(selectedresource.getNrpt()));
                }
            }
            else if (event.getClickCount() == 2) {
                resourceTableView.getSelectionModel().clearSelection();
                // La fila no se ha seleccionado, borra los campos de texto
                clearAllTextFields();
            }
        });
    }

    @FXML
    void addOrUpdate(ActionEvent event) {
        Resource selectedresource = resourceTableView.getSelectionModel().getSelectedItem();
        
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        int sectionId = Integer.parseInt(sectionTextField.getText());
        int nrpt = Integer.parseInt(nrptTextField.getText());

        if (selectedresource != null) {
            try {
                resourceDao.update(new Resource(id, name, sectionId, nrpt));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        else{
            try {
                resourceDao.insert(new Resource(id, name, sectionId, nrpt));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        clearAllTextFields();
        try {
            ObservableList<Resource> resources = FXCollections.observableArrayList(resourceDao.getAll());
            resourceTableView.setItems(resources);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Resource selectedresource = resourceTableView.getSelectionModel().getSelectedItem();
        try {
            resourceDao.delete(selectedresource.getId());
            ObservableList<Resource> resources = FXCollections.observableArrayList(resourceDao.getAll());
            resourceTableView.setItems(resources);
        } catch (SQLException e) {
            showAlert(e);
        }
    }

}

