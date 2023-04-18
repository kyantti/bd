package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.ProjectDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Project;

public class MssqlProjectDao implements ProjectDao{

    private Connection connection;

    @Override
    public Project get(Integer id) throws SQLException {
        connection = Database.getConnection();
        Project project = null;

        String select = "SELECT PRY_DenominacionC, PRY_DenominacionL, PRY_FechaInicio, PRY_Id_servicio  FROM PRY_Proyectos WHERE PRY_Id_proyecto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String tittle = resultSet.getString("PRY_DenominacionC");
                String description = resultSet.getString("PRY_DenominacionL");
                Date startDate = resultSet.getDate("PRY_FechaInicio");
                int serviceId = resultSet.getInt("PRY_Id_servicio");
                project = new Project(id, tittle, description, startDate, serviceId);

            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }

        return project;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        connection = Database.getConnection();

        String selectAll = "SELECT PRY_Id_proyecto, PRY_DenominacionC, PRY_DenominacionL, PRY_FechaInicio, PRY_Id_servicio FROM PRY_Proyectos";
        
        List <Project> projects = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int id = resultSet.getInt("PRY_Id_proyecto");
                String tittle = resultSet.getString("PRY_DenominacionC");
                String description = resultSet.getString("PRY_DenominacionL");
                Date startDate = resultSet.getDate("PRY_FechaInicio");
                int serviceId = resultSet.getInt("PRY_Id_servicio");
                projects.add(new Project(id, tittle, description, startDate, serviceId));

            }
        }

        return projects;
    }

    @Override
    public void insert(Project project) throws SQLException {
        connection = Database.getConnection();

        String insert = "INSERT INTO PRY_Proyectos (PRY_DenominacionC, PRY_DenominacionL, PRY_FechaInicio, PRY_Id_servicio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, project.getTittle());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, (java.sql.Date) project.getStartDate());
            preparedStatement.setInt(4, project.getServiceId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                project.setId(resultSet.getInt(1));
            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }

    }

    @Override
    public void update(Project project) throws SQLException {
        connection = Database.getConnection();

        String update = "UPDATE PRY_Proyectos set PRY_DenominacionC = ?, PRY_DenominacionL = ?, PRY_FechaInicio = ?, PRY_Id_servicio = ?, WHERE PRY_Id_proyecto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, project.getTittle());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, (java.sql.Date) project.getStartDate());
            preparedStatement.setInt(4, project.getServiceId());
            preparedStatement.setInt(5, project.getId());
            
            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        connection = Database.getConnection();

        String delete = "DELETE FROM PRY_Proyectos WHERE PRY_Id_proyecto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
    }
    
}
