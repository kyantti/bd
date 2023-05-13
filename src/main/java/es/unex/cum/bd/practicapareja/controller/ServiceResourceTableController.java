package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlServiceResourceDao;
import es.unex.cum.bd.practicapareja.model.entities.ServiceResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceResourceTableController implements Initializable {

    @FXML
    private TableView<ServiceResource> serviceResourceTableView;

    @FXML
    private TableColumn<ServiceResource, String> serviceIdCol;

    @FXML
    private TableColumn<ServiceResource, String> serviceDenominationCol;

    @FXML
    private TableColumn<ServiceResource, String> numberOfResourcesCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        MssqlServiceResourceDao serviceResourceDao = daoManager.getServiceResourceDao();
        try {
            ObservableList<ServiceResource> observableList = FXCollections.observableArrayList(serviceResourceDao.getAll());

            serviceIdCol.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
            serviceDenominationCol.setCellValueFactory(new PropertyValueFactory<>("serviceDenomination"));
            numberOfResourcesCol.setCellValueFactory(new PropertyValueFactory<>("numberOfResources"));
            
            serviceResourceTableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

}
