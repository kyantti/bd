package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.AddressDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Address;

public class MssqlAddressDao implements AddressDao {

    private Connection connection;

    public MssqlAddressDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Address get(Integer id) throws SQLException {
        Address address = null;

        String sql = "SELECT DGN_Id_dirgen, DGN_Denominacion FROM DIRECCIONES WHERE DGN_Id_dirgen = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                address = new Address(resultSet.getInt("DGN_Id_dirgen"),resultSet.getString("DGN_Denominacion"));
            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
        return address;
    }

    @Override
    public List<Address> getAll() throws SQLException {

        String sql = "SELECT DGN_Id_dirgen, DGN_Denominacion FROM DIRECCIONES";
        
        List <Address> addresses = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("DGN_Id_dirgen");
                String denomination = resultSet.getString("DGN_Denominacion");
                addresses.add(new Address(id, denomination));
            }
        }


        Collections.sort(addresses);

        return addresses;
    }

    @Override
    public void insert(Address address) throws SQLException {

        String sql = "INSERT INTO DIRECCIONES (DGN_Id_dirgen, DGN_Denominacion) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getDenomination());
            preparedStatement.executeUpdate();
            
            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }

    }

    @Override
    public void update(Address address) throws SQLException {

        String sql = "UPDATE DIRECCIONES set DGN_Denominacion = ? WHERE DGN_Id_dirgen = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, address.getDenomination());
            preparedStatement.setInt(2, address.getId());

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        String sql = "DELETE FROM DIRECCIONES WHERE DGN_Id_dirgen = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
            Database.closeConnection(connection);
        }
    }
    
}
