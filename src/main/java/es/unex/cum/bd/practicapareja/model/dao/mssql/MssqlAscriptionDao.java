package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.AscriptionDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Ascription;

public class MssqlAscriptionDao implements AscriptionDao{

    private Connection connection;

    public MssqlAscriptionDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public Ascription get(Ascription ascription) throws SQLException {

        Ascription newAscription = null;

        int sectionId = ascription.getSectionId();
        int resourceId = ascription.getResourceId();
        LocalDate ascriptionDate = ascription.getAscriptionDate();

        String select = "SELECT ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc FROM ADSCRIPCIONES WHERE ADS_Id_seccion = ? AND ADS_Id_recurso = ? AND ADS_FechaAdsc = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, sectionId);
            preparedStatement.setInt(2, resourceId);
            preparedStatement.setDate(3, java.sql.Date.valueOf(ascriptionDate));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int newSectionId = resultSet.getInt("ADS_Id_seccion");
                int newResourceId = resultSet.getInt("ADS_Id_recurso");
                Date newAscriptionDate = resultSet.getDate("ADS_FechaAdsc");
                newAscription = new Ascription(newSectionId, newResourceId, newAscriptionDate.toLocalDate());
            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
        }
        return newAscription;
    }

    @Override
    public List<Ascription> getAll() throws SQLException {
        String selectAll = "SELECT ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc FROM ADSCRIPCIONES";
        
        List <Ascription> ascriptions = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int sectionId = resultSet.getInt("ADS_Id_seccion");
                int resourceId = resultSet.getInt("ADS_Id_recurso");
                Date ascriptionDate = resultSet.getDate("ADS_FechaAdsc");

                ascriptions.add(new Ascription(sectionId, resourceId, ascriptionDate.toLocalDate()));
            }

            Database.closeResultSet(resultSet);
            Database.closeStatement(statement);
        }

        return ascriptions;
    }

    @Override
    public void insert(Ascription ascription) throws SQLException {
        String insert = "INSERT INTO ADSCRIPCIONES (ADS_Id_seccion, ADS_Id_recurso, ADS_FechaAdsc) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setInt(1, ascription.getSectionId());
            preparedStatement.setInt(2, ascription.getResourceId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(ascription.getAscriptionDate()));
            preparedStatement.executeUpdate();
            
            Database.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Ascription ascription) throws SQLException {

        String update = "UPDATE ADSCRIPCIONES set ADS_Id_seccion = ?, ADS_Id_recurso = ?, ADS_FechaAdsc = ? WHERE ADS_Id_seccion = ? AND ADS_Id_recurso = ? AND ADS_FechaAdsc = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setInt(1, ascription.getSectionId());
            preparedStatement.setInt(2, ascription.getResourceId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(ascription.getAscriptionDate()));

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Ascription ascription) throws SQLException {

        int sectionId = ascription.getSectionId();
        int resourceId = ascription.getResourceId();
        LocalDate ascriptionDate = ascription.getAscriptionDate();

        String delete = "DELETE FROM ADSCRIPCIONES WHERE ADS_Id_seccion = ? AND ADS_Id_recurso = ? AND ADS_FechaAdsc = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, sectionId);
            preparedStatement.setInt(2, resourceId);
            preparedStatement.setDate(3, java.sql.Date.valueOf(ascriptionDate));

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }

}
