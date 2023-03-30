package es.unex.cum.bd.practicapareja.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ascription {

    private int idSection;
    private int idResource;
    private Date ascriptionDate;

    public Ascription(int idSection, int idResource, Date ascriptionDate) {
        this.idSection = idSection;
        this.idResource = idResource;
        this.ascriptionDate = ascriptionDate;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public Date getAscriptionDate() {
        return ascriptionDate;
    }

    public void setAscriptionDate(Date ascriptionDate) {
        this.ascriptionDate = ascriptionDate;
    }

    public static List<Ascription> obtenerAscriptiones(Connection conexion) {
        List<Ascription> adscripciones = new ArrayList<>();
        String consulta = "SELECT ADS_Id_seccion, ADS_Id_recurso, ADS_DateAdsc FROM Adscriptiones";
        try (PreparedStatement pst = conexion.prepareStatement(consulta);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Ascription adsc = new Ascription(
                        rs.getInt("ADS_Id_section"),
                        rs.getInt("ADS_Id_resource"),
                        rs.getDate("ADS_DateAsc"));
                adscripciones.add(adsc);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener las adscripciones: " + ex.getMessage());
        }
        return adscripciones;
    }

    public void guardarAscription(Connection conexion) {
        String consulta = "INSERT INTO Ascriptiones (ADS_Id_section, ADS_Id_resource, ADS_DateAsc) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conexion.prepareStatement(consulta)) {
            pst.setInt(1, this.idSection);
            pst.setInt(2, this.idResource);
            pst.setDate(3, new java.sql.Date(this.ascriptionDate.getTime()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al guardar la adscripción: " + ex.getMessage());
        }
    }

    public void eliminarAscription(Connection conexion) {
        String consulta = "DELETE FROM Ascriptiones WHERE ADS_Id_section = ? AND ADS_Id_resource = ? AND ADS_DateAsc = ?";
        try (PreparedStatement pst = conexion.prepareStatement(consulta)) {
            pst.setInt(1, this.idSection);
            pst.setInt(2, this.idResource);
            pst.setDate(3, new java.sql.Date(this.ascriptionDate.getTime()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar la adscripción: " + ex.getMessage());
        }
    }
}
