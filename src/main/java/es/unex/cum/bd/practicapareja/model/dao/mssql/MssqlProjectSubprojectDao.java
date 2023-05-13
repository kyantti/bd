package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.entities.ProjectSubproject;

public class MssqlProjectSubprojectDao {

    private Connection connection;

    public MssqlProjectSubprojectDao(Connection connection) {
        this.connection = connection;
    }

    public List<ProjectSubproject> getAllWithout() throws SQLException {

        List<ProjectSubproject> list = new ArrayList<>();

        String selectAll = "SELECT PRY_DenominacionC as Nombre_corto_Proyecto, SBP_DenominacionL as Nombre_largo_Subproyecto FROM Subproyectos LEFT OUTER JOIN Asignaciones ON (SBP_Id_subproyecto = SGN_Id_subproyecto) INNER JOIN Proyectos on (SBP_Id_proyecto = PRY_Id_proyecto) WHERE SGN_Id_subproyecto is null ORDER BY 1, 2";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                String projectShortDenomination = resultSet.getString("Nombre_corto_Proyecto");
                String subprojectLongDenomination = resultSet.getString("Nombre_largo_Subproyecto");
                list.add(new ProjectSubproject(projectShortDenomination, subprojectLongDenomination));
            }
        }

        return list;
    }

    public List<ProjectSubproject> getAllWith() throws SQLException {

        String selectAll = "SELECT PRY_DenominacionC as Nombre_corto_Proyecto, SBP_DenominacionL as Nombre_largo_Subproyecto FROM Asignaciones as x INNER JOIN Subproyectos on (x.SGN_Id_subproyecto = SBP_Id_subproyecto) INNER JOIN Proyectos on (SBP_Id_proyecto = PRY_Id_proyecto) WHERE x.SGN_Id_recursoX NOT IN (SELECT SGN_Id_recursoX FROM Asignaciones WHERE (SGN_Id_subproyecto<>x.SGN_Id_subproyecto)) order by 1, 2";

        List<ProjectSubproject> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                String projectShortDenomination = resultSet.getString("Nombre_corto_Proyecto");
                String subprojectLongDenomination = resultSet.getString("Nombre_largo_Subproyecto");
                list.add(new ProjectSubproject(projectShortDenomination, subprojectLongDenomination));
            }
        }

        return list;
    }
}
