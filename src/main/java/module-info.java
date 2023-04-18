module es.unex.cum.bd.practica.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens es.unex.cum.bd.practicapareja.controller to javafx.fxml;
    exports es.unex.cum.bd.practicapareja.controller;
}
