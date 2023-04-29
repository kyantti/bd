package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.SectionDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Section;

public class MssqlSectionDao implements SectionDao {

    private Connection connection;

    public MssqlSectionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Section get(Integer id) throws SQLException {
        Section sections = null;

        String select = "SELECT SCC_Id_seccion, SCC_Denominacion FROM SECCIONES WHERE SCC_Id_seccion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sections = new Section(resultSet.getInt("DGN_Id_dirgen"),resultSet.getString("DGN_Denominacion"));
            }

            Database.closeResultSet(resultSet);
            Database.closePreparedStatement(preparedStatement);
        }
        return sections;
    }

    @Override
    public List<Section> getAll() throws SQLException {

        String selectAll = "SELECT SCC_Id_seccion, SCC_Denominacion FROM SECCIONES";
        
        List <Section> sections = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int id = resultSet.getInt("SCC_Id_seccion");
                String denomination = resultSet.getString("SCC_Denominacion");
                sections.add(new Section(id, denomination));
            }
        }

        return sections;
    }

    @Override
    public void insert(Section sections) throws SQLException {

        String insert = "INSERT INTO SECCIONES (SCC_Id_seccion, SCC_Denominacion) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setInt(1, sections.getId());
            preparedStatement.setString(2, sections.getDenomination());
            preparedStatement.executeUpdate();
            
            Database.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public void update(Section sections) throws SQLException {

        String update = "UPDATE SECCIONES set SCC_Denominacion = ? WHERE SCC_Id_seccion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, sections.getDenomination());
            preparedStatement.setInt(2, sections.getId());

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        String delete = "DELETE FROM SECCIONES WHERE SCC_Id_seccion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            Database.closePreparedStatement(preparedStatement);
        }
    }
}
