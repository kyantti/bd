package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;
import java.sql.SQLException;

import es.unex.cum.bd.practicapareja.model.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField userTextField;

    @FXML
    void connect(ActionEvent event) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Proyectos";
        String username = userTextField.getText();
        String password = passwordTextField.getText();

        try {
            Database.setConnection(url, username, password);
            App.setRoot("/es/unex/cum/bd/practicapareja/view/selectTable");
        } catch (SQLException | IOException e) {
            // Si hay un error de conexión, mostrar una ventana emergente con un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo conectar a la base de datos. Verifique su usuario y contraseña.");
            alert.showAndWait();
        }
    }

}