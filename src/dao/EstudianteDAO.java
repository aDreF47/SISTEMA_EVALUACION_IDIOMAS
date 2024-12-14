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

public class EstudianteDAO {
    
    public String alumnoPorCodigo(String codigoAlumno) {
    String nombreAlumno = "";
    String query = """
        SELECT u.nombre || ' ' || u.apellido AS nombreCompleto
        FROM Estudiante e
        JOIN Usuarios u ON e.idUsuario = u.idUsuario
        WHERE e.codigo = ?
    """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, codigoAlumno); // Código del alumno
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            nombreAlumno = rs.getString("nombreCompleto");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nombreAlumno;
    }
}





