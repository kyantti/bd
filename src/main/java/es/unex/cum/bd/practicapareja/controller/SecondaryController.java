package es.unex.cum.bd.practicapareja.controller;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("/es/unex/cum/bd/practicapareja/view/primary");
    }
}