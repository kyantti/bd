package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/es/unex/cum/bd/practicapareja/view/secondary");
    }
}
