package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.ServiceDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.entities.Service;
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

public class ServicesTableController implements Initializable {

    @FXML
    private TextField addressIdTextField;

    @FXML
    private TextField denominationTextField;

    @FXML
    private TableColumn<Service, String> addressIdCol;

    @FXML
    private TableColumn<Service, String> denominationCol;

    @FXML
    private TableColumn<Service, String> idCol;

    @FXML
    private TableView<Service> serviceTableView;

    @FXML
    private TextField idTextField;

    private ServiceDao serviceDao;

    private void clearAllTextFields(){
        idTextField.clear();
        denominationTextField.clear();
        addressIdTextField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        serviceDao = daoManager.getServiceDao();

        try {
            ObservableList<Service> services = FXCollections.observableArrayList(serviceDao.getAll());
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            denominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));
            addressIdCol.setCellValueFactory(new PropertyValueFactory<>("addressId"));
            serviceTableView.setItems(services);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        serviceTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Obtener la fila en la que se hizo clic
                Service selectedservice = serviceTableView.getSelectionModel().getSelectedItem();
        
                if (selectedservice != null) {
                    // La fila se ha seleccionado, puedes hacer algo con la información
                    idTextField.setText(String.valueOf(selectedservice.getId()));
                    denominationTextField.setText(selectedservice.getDenomination());
                    addressIdTextField.setText(String.valueOf(selectedservice.getAddressId()));
                }
            }
            else if (event.getClickCount() == 2) {
                serviceTableView.getSelectionModel().clearSelection();
                // La fila no se ha seleccionado, borra los campos de texto
                clearAllTextFields();
            }
        });
        
    }

    private void showAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    @FXML
    void addOrUpdateService(ActionEvent event) {
        Service selectedService = serviceTableView.getSelectionModel().getSelectedItem();

        int id = Integer.parseInt(idTextField.getText());
        String denomination = denominationTextField.getText();
        int addressId = Integer.parseInt(addressIdTextField.getText());

        if (selectedService != null) {
            try {
                serviceDao.update(new Service(id, denomination, addressId));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        else{
            try {
                serviceDao.insert(new Service(id, denomination, addressId));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        clearAllTextFields();
        try {
            ObservableList<Service> services = FXCollections.observableArrayList(serviceDao.getAll());
            serviceTableView.setItems(services);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteService(ActionEvent event) {
        Service selectedService = serviceTableView.getSelectionModel().getSelectedItem();
        try {
            serviceDao.delete(selectedService.getId());
            ObservableList<Service> services = FXCollections.observableArrayList(serviceDao.getAll());
            serviceTableView.setItems(services);
        } catch (SQLException e) {
            showAlert(e);
        }
    }   

}
