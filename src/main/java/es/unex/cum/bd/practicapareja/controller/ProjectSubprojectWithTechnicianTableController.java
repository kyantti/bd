package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlProjectSubprojectDao;
import es.unex.cum.bd.practicapareja.model.entities.ProjectSubproject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProjectSubprojectWithTechnicianTableController implements Initializable {
    @FXML
    private TableView<ProjectSubproject> projectSubprojectTableView;

    @FXML
    private TableColumn<ProjectSubproject, String> projectShortDenomination;

    @FXML
    private TableColumn<ProjectSubproject, String> subprojectLongDenomination;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        MssqlProjectSubprojectDao projectSubprojectDao = daoManager.getProjectSubprojectDao();
        try {
            ObservableList<ProjectSubproject> observableList = FXCollections.observableArrayList(projectSubprojectDao.getAllWith());

            projectShortDenomination.setCellValueFactory(new PropertyValueFactory<>("projectShortDenomination"));
            subprojectLongDenomination.setCellValueFactory(new PropertyValueFactory<>("subprojectLongDenomination"));
            
            projectSubprojectTableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

} 
