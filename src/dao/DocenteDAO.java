/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Conexion;
public class DocenteDAO {
    
    public String profesorExamen(int idExamen) {
        String nombreCompleto = "";
        String query = """
            SELECT u.nombre || ' ' || u.apellido AS nombreCompleto
            FROM Usuarios u
            JOIN UsuarioRol ur ON u.idUsuario = ur.idUsuario
            JOIN AsignacionDocenteExamen ad ON u.idUsuario = ad.idUsuario
            WHERE ur.idRol = 2 AND ad.idExamen = ?
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idExamen); // Reemplaza el '?' con el ID del examen
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombreCompleto = rs.getString("nombreCompleto"); // Extrae el nombre completo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreCompleto;
    }


}
