package es.unex.cum.bd.practicapareja.model.dao;

public interface DaoManager {
    AddressDao getAddressDao();
    ProjectDao getProjectDao();
    ServiceDao getServiceDao();
    AscriptionDao getAscriptionDao();
    SectionDao getSectionDao();
    ResourceDao getResourceDao();
}
