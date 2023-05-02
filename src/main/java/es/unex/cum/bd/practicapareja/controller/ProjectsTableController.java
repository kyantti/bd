package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.ProjectDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.entities.Project;
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

public class ProjectsTableController implements Initializable {

    @FXML
    private TableColumn<Project, String> descriptionCol;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TableColumn<Project, String> projectIdCol;

    @FXML
    private TableColumn<Project, String> projectServiceIdCol;

    @FXML
    private TableView<Project> projectTableView;

    @FXML
    private TextField serviceIdTextField;

    @FXML
    private TableColumn<Project, String> startDateCol;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<Project, String> tittleCol;

    @FXML
    private TextField tittleTextField;

    @FXML
    private TextField idTextField;

    private ProjectDao projectDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        projectDao = daoManager.getProjectDao();

        try {
            ObservableList<Project> projects = FXCollections.observableArrayList(projectDao.getAll());
            projectIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            tittleCol.setCellValueFactory(new PropertyValueFactory<>("tittle"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            projectServiceIdCol.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
            projectTableView.setItems(projects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        projectTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Obtener la fila en la que se hizo clic
                Project selectedProject = projectTableView.getSelectionModel().getSelectedItem();
        
                if (selectedProject != null) {
                    // La fila se ha seleccionado, puedes hacer algo con la información
                    idTextField.setText(String.valueOf(selectedProject.getId()));
                    tittleTextField.setText(selectedProject.getTittle());
                    descriptionTextField.setText(selectedProject.getDescription());
                    startDatePicker.setValue(selectedProject.getStartDate());
                    serviceIdTextField.setText(String.valueOf(selectedProject.getServiceId()));
                }
            }
            else if (event.getClickCount() == 2) {
                projectTableView.getSelectionModel().clearSelection();
                // La fila no se ha seleccionado, borra los campos de texto
                clearAllTextFields();
            }
        });
        
    }

    private void clearAllTextFields() {
        idTextField.clear();
        tittleTextField.clear();
        descriptionTextField.clear();
        startDatePicker.setValue(null);
        serviceIdTextField.clear();
    }

    private void showAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    @FXML
    void deleteProject(ActionEvent event) {
        Project selectedProject = projectTableView.getSelectionModel().getSelectedItem();
        try {
            projectDao.delete(selectedProject.getId());
            ObservableList<Project> projects = FXCollections.observableArrayList(projectDao.getAll());
            projectTableView.setItems(projects);
        } catch (SQLException e) {
            showAlert(e);
        }
    }

    @FXML
    void addOrUpdateProject(ActionEvent event) {
        Project selectedProject = projectTableView.getSelectionModel().getSelectedItem();
        
        int id = Integer.parseInt(idTextField.getText());
        String tittle = tittleTextField.getText();
        String description = descriptionTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        int serviceId = Integer.parseInt(serviceIdTextField.getText());

        if (selectedProject != null) {
            try {
                projectDao.update(new Project(id, tittle, description, startDate, serviceId));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        else{
            try {
                projectDao.insert(new Project(id, tittle, description, startDate, serviceId));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        clearAllTextFields();
        try {
            ObservableList<Project> projects = FXCollections.observableArrayList(projectDao.getAll());
            projectTableView.setItems(projects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
