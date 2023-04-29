package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.AddressDao;
import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.entities.Address;
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

public class AddressesTableController implements Initializable {

    @FXML
    private TableView<Address> addressTableView;

    @FXML
    private TableColumn<Address, String> denominationCol;

    @FXML
    private TextField denominationTextField;

    @FXML
    private TableColumn<Address, String> idCol;

    private AddressDao addressDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        addressDao = daoManager.getAddressDao();
        try {
            ObservableList<Address> addresses = FXCollections.observableArrayList(addressDao.getAll());
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            denominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));
            addressTableView.setItems(addresses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addressTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Obtener la fila en la que se hizo clic
                Address selectedaddress = addressTableView.getSelectionModel().getSelectedItem();
        
                if (selectedaddress != null) {
                    // La fila se ha seleccionado, puedes hacer algo con la información
                    denominationTextField.setText(selectedaddress.getDenomination());
                }
            }
            else if (event.getClickCount() == 2) {
                addressTableView.getSelectionModel().clearSelection();
                // La fila no se ha seleccionado, borra los campos de texto
                denominationTextField.clear();
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
    void setDenomination(ActionEvent event) {

    }


    @FXML
    void addOrUpdateAddress(ActionEvent event) {
        Address selectedAddress = addressTableView.getSelectionModel().getSelectedItem();
        
        int id = 0;
        String denomination = denominationTextField.getText();

        if (selectedAddress != null) {
            System.out.println("update");
            id = selectedAddress.getId();
            try {
                addressDao.update(new Address(id, denomination));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        else{
            System.out.println("insert");
            try {
                id = addressDao.getAll().size() + 1;
                addressDao.insert(new Address(id, denomination));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        try {
            ObservableList<Address> addresss = FXCollections.observableArrayList(addressDao.getAll());
            addressTableView.setItems(addresss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteAddress(ActionEvent event) {
        Address selectedAddress = addressTableView.getSelectionModel().getSelectedItem();
        try {
            addressDao.delete(selectedAddress.getId());
            ObservableList<Address> addresss = FXCollections.observableArrayList(addressDao.getAll());
            addressTableView.setItems(addresss);
        } catch (SQLException e) {
            showAlert(e);
        }
    }

}
