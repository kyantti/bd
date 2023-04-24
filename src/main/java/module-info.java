module es.unex.cum.bd.practica.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    
    opens es.unex.cum.bd.practicapareja.model.dao;
    opens es.unex.cum.bd.practicapareja.model.dao.mssql;
    opens es.unex.cum.bd.practicapareja.model.entities;
    opens es.unex.cum.bd.practicapareja.controller to javafx.fxml;
    exports es.unex.cum.bd.practicapareja.controller;
}
