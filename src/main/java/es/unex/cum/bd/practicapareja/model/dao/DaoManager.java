package es.unex.cum.bd.practicapareja.model.dao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlProjectSubprojectDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlServiceResourceDao;

public interface DaoManager {
    AddressDao getAddressDao();
    ProjectDao getProjectDao();
    ServiceDao getServiceDao();
    AscriptionDao getAscriptionDao();
    SectionDao getSectionDao();
    ResourceDao getResourceDao();
    
    MssqlProjectSubprojectDao getProjectSubprojectDao();
    MssqlServiceResourceDao getServiceResourceDao();
}
