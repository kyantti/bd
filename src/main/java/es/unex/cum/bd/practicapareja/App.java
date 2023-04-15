package es.unex.cum.bd.practicapareja;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlAddressDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlProjectDao;
import es.unex.cum.bd.practicapareja.model.entities.Address;
import es.unex.cum.bd.practicapareja.model.entities.Project;

public class App {

    private Scanner scanner;

    private App() {
        scanner = new Scanner(System.in);
    }

    
    private void performQuery() throws SQLException{
        MssqlAddressDao addressDao = new MssqlAddressDao();
        MssqlProjectDao projectDao = new MssqlProjectDao();
        //addressDao.insert(new Address("C.Almorchón 13"));
        addressDao.update(new Address(11, "C.Cabeza del buey"));
        for (Address address : addressDao.getAll()) {
            System.out.println(address.toString());
        }
        //System.out.println(addressDao.get(2).toString());

    }

    public static void main(String[] args) throws SQLException {
        App app = new App();
        app.performQuery();

    }
}
