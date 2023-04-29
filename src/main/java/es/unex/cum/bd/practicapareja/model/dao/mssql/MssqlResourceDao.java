package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.ResourceDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Resource;

public class MssqlResourceDao implements ResourceDao {
    private Connection connection;

    public MssqlResourceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Resource get(Integer id) throws SQLException {
        Resource resource = null;

        String select = "SELECT RCR_Id_recurso, RCR_nombre, RCR_Id_seccion, RCR_NRPT FROM RECURSOS WHERE RCR_Id_recurso = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int newId = resultSet.getInt("DGN_Id_dirgen");
                String name = resultSet.getString("RCR_nombre");
                String sectionId = resultSet.getString("RCR_Id_seccion");
                int nrpt = resultSet.getInt("RCR_NRPT");
                resource = new Resource(newId, name, sectionId, nrpt);
            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
        }
        return resource;
    }

    @Override
    public List<Resource> getAll() throws SQLException {

        String selectAll = "SELECT RCR_Id_recurso, RCR_nombre, RCR_Id_seccion, RCR_NRPT FROM RECURSOS";
        
        List <Resource> resources = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int id = resultSet.getInt("RCR_Id_recurso");
                String name = resultSet.getString("RCR_nombre");
                String sectionId = resultSet.getString("RCR_Id_seccion");
                int nrpt = resultSet.getInt("RCR_NRPT");
                resources.add(new Resource(id, name, sectionId, nrpt));
            }
        }

        return resources;
    }

    @Override
    public void insert(Resource resource) throws SQLException {

        String insert = "INSERT INTO RECURSOS (RCR_Id_recurso, RCR_nombre, RCR_Id_seccion, RCR_NRPT) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setInt(1, resource.getId());
            preparedStatement.setString(2, resource.getName());
            preparedStatement.setString(3, resource.getSectionId());
            preparedStatement.setInt(4, resource.getNrpt());
            preparedStatement.executeUpdate();
            
            Database.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public void update(Resource resource) throws SQLException {

        String update = "UPDATE RECURSOS set RCR_nombre = ?, RCR_Id_seccion = ?, RCR_NRPT = ? WHERE RCR_Id_recurso = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, resource.getName());
            preparedStatement.setString(2, resource.getSectionId());
            preparedStatement.setInt(3, resource.getNrpt());
            preparedStatement.setInt(4, resource.getId());

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        String delete = "DELETE FROM RECURSOS WHERE RCR_Id_recurso = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }
}
