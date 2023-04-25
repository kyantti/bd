package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.AddressDao;
import es.unex.cum.bd.practicapareja.model.dao.ProjectDao;
import es.unex.cum.bd.practicapareja.model.dao.ServiceDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlAddressDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlProjectDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlServiceDao;
import es.unex.cum.bd.practicapareja.model.entities.Address;
import es.unex.cum.bd.practicapareja.model.entities.Project;
import es.unex.cum.bd.practicapareja.model.entities.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class PrimaryController implements Initializable {

    // addresess
    @FXML
    private TableView<Address> addressTableView;

    @FXML
    private TableColumn<Address, Integer> addressIdCol;

    @FXML
    private TableColumn<Address, String> addressDenominationCol;

    private AddressDao addressDao;

    // projects
    @FXML
    private TableView<Project> projectTableView;

    @FXML
    private TableColumn<Project, Integer> projectIdCol;

    @FXML
    private TableColumn<Project, String> tittleCol;

    @FXML
    private TableColumn<Project, String> descriptionCol;

    @FXML
    private TableColumn<Project, String> startDateCol;

    @FXML
    private TableColumn<Project, String> projectServiceIdCol;

    private ProjectDao projectDao;

    // services

    @FXML
    private TableView<Service> serviceTableView;

    @FXML
    private TableColumn<Service, Integer> serviceIdCol;

    @FXML
    private TableColumn<Service, String> serviceDenominationCol;

    @FXML
    private TableColumn<Service, Integer> serviceAddressIdCol;

    private ServiceDao serviceDao;

    // other

    @FXML
    private TableView<?> tableView;

    @FXML
    private ToggleGroup tables;

    @FXML
    void manageAddresses(ActionEvent event) throws SQLException {
        addressTableView.setVisible(true);
        projectTableView.setVisible(false);
        serviceTableView.setVisible(false);
        addressTableView.setVisible(false);

        // get the data you need to display in the tableview
        ObservableList<Address> addresses = FXCollections.observableArrayList(addressDao.getAll());

        // update the tableview to show the data
        addressTableView.setItems(addresses);
        addressIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressDenominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));
    }

    @FXML
    void editAddresses(ActionEvent event) {

    }

    @FXML
    void manageCompanies(ActionEvent event) {

    }

    @FXML
    void manageProjects(ActionEvent event) {
        addressTableView.setVisible(false);
        projectTableView.setVisible(true);
        serviceTableView.setVisible(false);
        addressTableView.setVisible(false);

        // get the data you need to display in the tableview
        ObservableList<Project> projects;
        try {
            projects = FXCollections.observableArrayList(projectDao.getAll());
            // update the tableview to show the data
            projectTableView.setItems(projects);
            tittleCol.setCellFactory(TextFieldTableCell.forTableColumn());
            descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
            startDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            projectServiceIdCol.setCellFactory(TextFieldTableCell.forTableColumn());

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @FXML
    void manageServices(ActionEvent event) {
        addressTableView.setVisible(false);
        projectTableView.setVisible(false);
        serviceTableView.setVisible(true);
        addressTableView.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressDao = new MssqlAddressDao();
        projectDao = new MssqlProjectDao();
        serviceDao = new MssqlServiceDao();

        tableView.setVisible(false);
        projectTableView.setVisible(false);
        serviceTableView.setVisible(false);
        addressTableView.setVisible(true);

        ObservableList<Address> addresses;
        try {
            addresses = FXCollections.observableArrayList(addressDao.getAll());
            addressTableView.setItems(addresses);
            addressIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            addressDenominationCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressDenominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));

            addressDenominationCol.setOnEditCommit(event -> {
                Address address = event.getRowValue();
                address.setDenomination(event.getNewValue());
                try {
                    addressDao.update(address);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

            addressTableView.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.DELETE)) {
                    Address selectedAddress = addressTableView.getSelectionModel().getSelectedItem();
                    try {
                        addressDao.delete(selectedAddress.getId());
                        addresses.remove(selectedAddress);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

            addressTableView.setRowFactory(tv -> {
                TableRow<Address> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && row.isEmpty()) {
                        Address newAddress = new Address(null);
                        if (addresses.isEmpty()) {
                            //newAddress.setId(1);
                        } else {
                            int lastId;
                            try {
                                int size = addressDao.getAll().size();
                                System.out.println(size);
                                lastId = addressDao.get(size - 1).getId();
                                newAddress.setId(lastId + 1);
                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } 
                        }
                        addresses.add(newAddress);
                        addressTableView.getSelectionModel().select(newAddress);
                        addressTableView.scrollTo(newAddress);
                        addressTableView.edit(addressTableView.getSelectionModel().getSelectedIndex(), addressDenominationCol);
                    }
                    
                });
                return row;
            });

            addressDenominationCol.setOnEditCommit(event -> {
                Address address = event.getRowValue();
                address.setDenomination(event.getNewValue());
                try {
                    addressDao.insert(address);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            
            
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
