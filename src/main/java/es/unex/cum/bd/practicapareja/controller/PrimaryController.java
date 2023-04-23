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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;


public class PrimaryController implements Initializable {

    //addresess
    @FXML
    private TableView<Address> addressTableView;

    @FXML
    private TableColumn<Address, Integer> addressIdCol;

    @FXML
    private TableColumn<Address, String> addressDenominationCol;

    private AddressDao addressDao;

    //projects
    @FXML
    private TableView<Project> projectTableView;

    @FXML
    private TableColumn<Project, Integer> projectIdCol;

    @FXML
    private TableColumn<Project, String> tittleCol;

    @FXML
    private TableColumn<Project, String> descriptionCol;

    @FXML
    private TableColumn<Project, Date> startDateCol;

    @FXML
    private TableColumn<Project, Integer> projectServiceIdCol;

    private ProjectDao projectDao;

    //services

    @FXML
    private TableView<Service> serviceTableView;

    @FXML
    private TableColumn<Service, Integer> serviceIdCol;

    @FXML
    private TableColumn<Service, String> serviceDenominationCol;

    @FXML
    private TableColumn<Service, Integer> serviceAddressIdCol;

    private ServiceDao serviceDao;

    //other

    @FXML
    private TableView<?> tableView;


    @FXML
    private ToggleGroup tables;

    

    @FXML
    void manageAddresses(ActionEvent event) throws SQLException {
        addressTableView.setVisible(true);
        projectTableView.setVisible(false);
        serviceTableView.setVisible(false);
        tableView.setVisible(false);

        // get the data you need to display in the tableview
        ObservableList<Address> addresses = FXCollections.observableArrayList(addressDao.getAll());

        // update the tableview to show the data
        addressTableView.setItems(addresses);
        addressIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressDenominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));
    }

    @FXML
    void manageCompanies(ActionEvent event) {

    }

    @FXML
    void manageProjects(ActionEvent event) {
        addressTableView.setVisible(false);
        projectTableView.setVisible(true);
        serviceTableView.setVisible(false);
        tableView.setVisible(false);
    }

    @FXML
    void manageServices(ActionEvent event) {
        addressTableView.setVisible(false);
        projectTableView.setVisible(false);
        serviceTableView.setVisible(true);
        tableView.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressDao = new MssqlAddressDao();
        projectDao = new MssqlProjectDao();
        serviceDao = new MssqlServiceDao();

        projectTableView.setVisible(false);
        serviceTableView.setVisible(false);
        tableView.setVisible(false);

        ObservableList<Address> addresses;
        try {
            addresses = FXCollections.observableArrayList(addressDao.getAll());
            // update the tableview to show the data
            addressTableView.setItems(addresses);
            addressIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            addressDenominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
