package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.ProjectDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Project;

public class MssqlProjectDao implements ProjectDao{

    private Connection connection;

    public MssqlProjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Project get(Integer id) throws SQLException {
        Project project = null;

        String select = "SELECT PRY_DenominacionC, PRY_DenominacionL, PRY_FechaInicio, PRY_Id_servicio  FROM Proyectos WHERE PRY_Id_proyecto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String tittle = resultSet.getString("PRY_DenominacionC");
                String description = resultSet.getString("PRY_DenominacionL");
                LocalDate startDate = resultSet.getDate("PRY_FechaInicio").toLocalDate();
                int serviceId = resultSet.getInt("PRY_Id_servicio");
                project = new Project(id, tittle, description, startDate, serviceId);

            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
        }

        return project;
    }

    @Override
    public List<Project> getAll() throws SQLException {

        String selectAll = "SELECT PRY_Id_proyecto, PRY_DenominacionC, PRY_DenominacionL, PRY_FechaInicio, PRY_Id_servicio FROM Proyectos";
        
        List <Project> projects = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int id = resultSet.getInt("PRY_Id_proyecto");
                String tittle = resultSet.getString("PRY_DenominacionC");
                String description = resultSet.getString("PRY_DenominacionL");
                LocalDate startDate = resultSet.getDate("PRY_FechaInicio").toLocalDate();
                int serviceId = resultSet.getInt("PRY_Id_servicio");
                projects.add(new Project(id, tittle, description, startDate, serviceId));

            }
        }

        return projects;
    }

    @Override
    public void insert(Project project) throws SQLException {

        String insert = "INSERT INTO Proyectos (PRY_Id_proyecto, PRY_DenominacionC, PRY_DenominacionL, PRY_FechaInicio, PRY_Id_servicio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setString(2, project.getTittle());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setDate(4, java.sql.Date.valueOf(project.getStartDate()));
            preparedStatement.setInt(5, project.getServiceId());
            preparedStatement.executeUpdate();

            
            Database.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public void update(Project project) throws SQLException {

        String update = "UPDATE Proyectos set PRY_DenominacionC = ?, PRY_DenominacionL = ?, PRY_FechaInicio = ?, PRY_Id_servicio = ? WHERE PRY_Id_proyecto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, project.getTittle());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
            preparedStatement.setInt(4, project.getServiceId());
            preparedStatement.setInt(5, project.getId());
            
            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        String delete = "DELETE FROM Proyectos WHERE PRY_Id_proyecto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }
    
}
