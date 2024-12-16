
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.Conexion;

public class MatriculaDAO {

    // Método para obtener cursos matriculados por un estudiante
    public List<String> obtenerCursosMatriculados(int idEstudiante) {
        List<String> cursos = new ArrayList<>();
        String query = "SELECT H.idHorario, H.horaInicio, H.horaFin, H.diaSemana, " +
                       "M.nombre AS nombreModulo, U.nombre || ' ' || U.apellido AS nombreDocente " +
                       "FROM Matricula MA " +
                       "JOIN AsignacionDocentes AD ON MA.idAsignacion = AD.idAsignacion " +
                       "JOIN Horario H ON AD.idHorario = H.idHorario " +
                       "JOIN Modulo M ON H.idModulo = M.idModulo " +
                       "JOIN Usuario U ON AD.idUsuario = U.idUsuario " +
                       "WHERE MA.idEstudiante = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idEstudiante);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String curso = String.format(
                    "Horario: %s - %s, Día: %s, Módulo: %s, Docente: %s",
                    rs.getString("horaInicio"),
                    rs.getString("horaFin"),
                    rs.getString("diaSemana"),
                    rs.getString("nombreModulo"),
                    rs.getString("nombreDocente")
                );
                cursos.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
