package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;

import es.unex.cum.bd.practicapareja.model.dao.AddressDao;
import es.unex.cum.bd.practicapareja.model.dao.AscriptionDao;
import es.unex.cum.bd.practicapareja.model.dao.DaoManager;
import es.unex.cum.bd.practicapareja.model.dao.ProjectDao;
import es.unex.cum.bd.practicapareja.model.dao.ResourceDao;
import es.unex.cum.bd.practicapareja.model.dao.SectionDao;
import es.unex.cum.bd.practicapareja.model.dao.ServiceDao;
import es.unex.cum.bd.practicapareja.model.database.Database;

public class MssqlDaoManager implements  DaoManager{

    private static MssqlDaoManager instance;
    private Connection connection = null;
    private AddressDao addressDao = null;
    private ProjectDao projectDao = null;
    private ServiceDao serviceDao = null;
    private AscriptionDao ascriptionDao = null;
    private SectionDao sectionDao = null;
    private ResourceDao resourceDao = null;
    
    private MssqlProjectSubprojectDao projectSubprojectDao = null;
    private MssqlServiceResourceDao serviceResourceDao = null;


    private MssqlDaoManager() {
        connection = Database.getConnection();
    }

    public static MssqlDaoManager getInstance() {
        if (instance == null) {
            instance = new MssqlDaoManager();
        }
        return instance;
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

    @Override
    public AscriptionDao getAscriptionDao() {
        if (ascriptionDao == null) {
            ascriptionDao = new MssqlAscriptionDao(connection);
        }
        return ascriptionDao;
    }

    @Override
    public SectionDao getSectionDao() {
        if (sectionDao == null) {
            sectionDao = new MssqlSectionDao(connection);
        }
        return sectionDao;
    }

    @Override
    public ResourceDao getResourceDao() {
        if (resourceDao == null) {
            resourceDao = new MssqlResourceDao(connection);
        }
        return resourceDao;
    }

    @Override
    public MssqlProjectSubprojectDao getProjectSubprojectDao() {
        if (projectSubprojectDao == null) {
            projectSubprojectDao = new MssqlProjectSubprojectDao(connection);
        }
        return projectSubprojectDao;
    }

    @Override
    public MssqlServiceResourceDao getServiceResourceDao() {
        if (serviceResourceDao == null) {
            serviceResourceDao = new MssqlServiceResourceDao(connection);
        }
        return serviceResourceDao;
    }
    
}
