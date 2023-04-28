package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import es.unex.cum.bd.practicapareja.model.dao.AddressDao;
import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.ProjectDao;
import es.unex.cum.bd.practicapareja.model.dao.ServiceDao;
import es.unex.cum.bd.practicapareja.model.database.Database;

public class MssqlDaoManager implements  DaoManager{

    private Connection connection = null;
    private AddressDao addressDao = null;
    private ProjectDao projectDao = null;
    private ServiceDao serviceDao = null;

    public MssqlDaoManager(String host, String username, String password) throws SQLException{
        Database.getConnection(host, username, password);
    }

    @Override
    public AddressDao getAddressDao() {
       if (addressDao == null) {
           addressDao = new MssqlAddressDao(connection);
       }
       return addressDao;
    }

    @Override
    public ProjectDao getProjectDao() {
        if (projectDao == null) {
            projectDao = new MssqlProjectDao(connection);
        }
        return projectDao;
    }

    @Override
    public ServiceDao getServiceDao() {
       if (serviceDao == null) {
           serviceDao = new MssqlServiceDao(connection);
       }
       return serviceDao;
    }
    
}
