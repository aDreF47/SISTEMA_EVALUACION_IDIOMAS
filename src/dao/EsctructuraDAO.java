/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author nando
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.EstructuraEva;
import utils.Conexion;

public class EsctructuraDAO {

    public EstructuraEva obtenerEstructuraPorId(String idEstructura) {
        EstructuraEva estructura = null;
        String query = """
            SELECT idEstructura, tipoExamen, puntosPorPregunta, duracion
            FROM EstructuraEva
            WHERE idEstructura = ?
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, idEstructura); // Reemplaza el '?' con el ID de la estructura
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estructura = new EstructuraEva(
                        rs.getString("idEstructura"),
                        rs.getString("tipoExamen"),
                        rs.getInt("puntosPorPregunta"),
                        rs.getInt("duracion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estructura;
    }
}
