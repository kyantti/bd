package es.unex.cum.bd.practicapareja.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Address {

    private int id;
    private String denomination;

    public Address(int id, String denomination) {
        this.id = id;
        this.denomination = denomination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public static List<Address> getAddresses(Connection conn) throws SQLException {
        List<Address> addresses = new ArrayList<>();

        String query = "SELECT * FROM PRY_Direcciones";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("DGN_Id_dirgen");
            String denomination = rs.getString("DGN_Denominacion");
            Address address = new Address(id, denomination);
            addresses.add(address);
        }

        return addresses;
    }

    public static void addAddress(Connection conn, String denomination) throws SQLException {
        String query = "INSERT INTO PRY_Direcciones (DGN_Id_dirgen, DGN_Denominacion) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, getAddresses(conn).size());
        pstmt.setString(2, denomination);
        pstmt.executeUpdate();
    }

    public static void deleteAddress(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM PRY_Direcciones WHERE DGN_Id_dirgen = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

