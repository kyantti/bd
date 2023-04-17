package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.SQLException;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.dao.ServiceDao;
import es.unex.cum.bd.practicapareja.model.entities.Service;

public class MssqlServiceDao implements ServiceDao{

    @Override
    public Service get(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Service> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void insert(Service service) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Service service) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
