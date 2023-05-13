package es.unex.cum.bd.practicapareja.model.dao.mssql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.bd.practicapareja.model.entities.ServiceResource;

public class MssqlServiceResourceDao {
    private Connection connection;

    public MssqlServiceResourceDao(Connection connection) {
        this.connection = connection;
    }

    public List<ServiceResource> getAll() throws SQLException {

        String selectAll = "select SRV_Id_servicio, SRV_Denominacion, count(SBP_Id_recursoD) as NRecursos " +
                "into #t " +
                "from Servicios " +
                "inner join Proyectos on (SRV_Id_servicio = PRY_Id_servicio) inner join Subproyectos on (PRY_Id_proyecto = SBP_Id_proyecto) "
                +
                "inner join Desarrollo on (SBP_Id_subproyecto = DSR_Id_subproyecto) " +
                "where DSR_FechaAdsc = (select max(DSR_FechaAdsc) from Desarrollo where (DSR_FechaAdsc <= getdate())) "
                +
                "group by SRV_Id_servicio, SRV_Denominacion " +
                " " +
                "UNION ALL " +
                " " +
                "select SRV_Id_servicio, SRV_Denominacion, count(SBP_Id_recursoE) " +
                "from Servicios " +
                "inner join Proyectos on (SRV_Id_servicio = PRY_Id_servicio) inner join Subproyectos on (PRY_Id_proyecto = SBP_Id_proyecto) "
                +
                "inner join Explotacion on SBP_Id_subproyecto = XPL_Id_subproyecto " +
                "where XPL_FechaAdsc = (select max(XPL_FechaAdsc) from Explotacion where (XPL_FechaAdsc <= getdate())) "
                +
                "group by SRV_Id_servicio, SRV_Denominacion " +
                " " +
                "UNION all " +
                " " +
                "select SRV_Id_servicio, SRV_Denominacion, count(SGN_Id_recursoX) " +
                "from Servicios  " +
                "inner join Proyectos on (SRV_Id_servicio = PRY_Id_servicio) inner join Subproyectos on (PRY_Id_proyecto = SBP_Id_proyecto) "
                +
                "inner join Asignaciones on (SBP_Id_proyecto = SGN_Id_subproyecto) " +
                "group by SRV_Id_servicio, SRV_Denominacion " +
                " " +
                "select SRV_Id_servicio as Codigo_de_Servicio, SRV_Denominacion as Nombre_Servicio, sum(NRecursos) as Número_Recursos "
                +
                "into #v " +
                "from #t " +
                "group by SRV_Id_servicio, SRV_Denominacion " +
                " " +
                "select * " +
                "from #v " +
                "where Número_Recursos = (select max(Número_Recursos) from #v) " +
                " " +
                "drop table #v " +
                "drop table #t";

        List<ServiceResource> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);

            while (resultSet.next()) {
                int serviceId = resultSet.getInt("Codigo_de_Servicio");
                String serviceDenomination = resultSet.getString("Nombre_Servicio");
                int numberOfResources = resultSet.getInt("Número_Recursos");

                list.add(new ServiceResource(serviceId, serviceDenomination, numberOfResources));
            }
        }

        return list;
    }
}
