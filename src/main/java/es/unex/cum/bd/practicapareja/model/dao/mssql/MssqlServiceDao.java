package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.ServiceDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Service;

public class MssqlServiceDao implements ServiceDao{

    private Connection connection;

    public MssqlServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Service get(Integer id) throws SQLException {
        Service service = null;

        String select = "SELECT SRV_Denominacion, SRV_Id_dirgen FROM Servicios WHERE SRV_Id_servicio = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String denomination = resultSet.getString("SRV_Denominacion");
                int addressId = resultSet.getInt("SRV_Id_dirgen");
                service = new Service(denomination, addressId);
            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }

        return service;
    }

    @Override
    public List<Service> getAll() throws SQLException {

        String selectAll = "SELECT SRV_Id_servicio, SRV_Denominacion, SRV_Id_dirgen FROM Servicios";
        
        List <Service> services = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int id = resultSet.getInt("SRV_Id_servicio");
                String denomination = resultSet.getString("SRV_Denominacion");
                int addressId = resultSet.getInt("SRV_Id_dirgen");
                services.add(new Service(id, denomination, addressId));

            }
        }

        return services;
    }

    @Override
    public void insert(Service service) throws SQLException {

        String insert = "INSERT INTO Servicios (SRV_Id_servicio, SRV_Denominacion, SRV_Id_dirgen) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, service.getId());
            preparedStatement.setString(2, service.getDenomination());
            preparedStatement.setInt(3, service.getAddressId());
            
            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }

    }

    @Override
    public void update(Service service) throws SQLException {

        String update = "UPDATE Servicios set SRV_Denominacion = ?, SRV_Id_dirgen = ? WHERE SRV_Id_servicio = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, service.getDenomination());
            preparedStatement.setInt(2, service.getAddressId());
            preparedStatement.setInt(3, service.getId());
            
            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        String delete = "DELETE FROM Servicios WHERE SRV_Id_servicio = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
    }
    
}
