package es.unex.cum.bd.practicapareja.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.SectionDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlDaoManager;
import es.unex.cum.bd.practicapareja.model.entities.Section;
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

public class SectionsTableController implements Initializable{

    @FXML
    private TableColumn<Section, String> denominationCol;

    @FXML
    private TextField denominationTextField;

    @FXML
    private TableColumn<Section, String> idCol;

    @FXML
    private TableView<Section> sectionTableView;

    @FXML
    private TextField idTextField;

    private SectionDao sectionDao;

    private void showAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    private void clearAllTextFields(){
        idTextField.clear();
        denominationTextField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoManager daoManager = MssqlDaoManager.getInstance();
        sectionDao = daoManager.getSectionDao();
        try {
            ObservableList<Section> sectiones = FXCollections.observableArrayList(sectionDao.getAll());
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            denominationCol.setCellValueFactory(new PropertyValueFactory<>("denomination"));
            sectionTableView.setItems(sectiones);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sectionTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Obtener la fila en la que se hizo clic
                Section selectedSection = sectionTableView.getSelectionModel().getSelectedItem();
        
                if (selectedSection != null) {
                    // La fila se ha seleccionado, puedes hacer algo con la información
                    idTextField.setText(String.valueOf(selectedSection.getId()));
                    denominationTextField.setText(selectedSection.getDenomination());
                }
            }
            else if (event.getClickCount() == 2) {
                sectionTableView.getSelectionModel().clearSelection();
                // La fila no se ha seleccionado, borra los campos de texto
                clearAllTextFields();
            }
        });
    }

    @FXML
    void addOrUpdate(ActionEvent event) {
        Section selectedSection = sectionTableView.getSelectionModel().getSelectedItem();
        
        int id = Integer.parseInt(idTextField.getText());
        String denomination = denominationTextField.getText();

        if (selectedSection != null) {
            try {
                sectionDao.update(new Section(id, denomination));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        else{
            try {
                sectionDao.insert(new Section(id, denomination));
            } catch (SQLException e) {
                showAlert(e);
            }
        }
        denominationTextField.clear();
        try {
            ObservableList<Section> sections = FXCollections.observableArrayList(sectionDao.getAll());
            sectionTableView.setItems(sections);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Section selectedSection = sectionTableView.getSelectionModel().getSelectedItem();
        try {
            sectionDao.delete(selectedSection.getId());
            ObservableList<Section> sections = FXCollections.observableArrayList(sectionDao.getAll());
            sectionTableView.setItems(sections);
        } catch (SQLException e) {
            showAlert(e);
        }
    }

}
