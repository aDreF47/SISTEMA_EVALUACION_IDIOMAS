package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.HorarioDisponible;
import utils.Conexion;

public class HorarioDAO {
    public ObservableList<HorarioDisponible> obtenerHorariosDisponibles() {
        ObservableList<HorarioDisponible> listaHorarios = FXCollections.observableArrayList();
    
        String query = "SELECT m.NOMBRE || ' '|| m.NUMEROMODULO AS Idioma," +
                       "TO_CHAR(h.horaInicio, 'HH24:MI') || ' - ' || TO_CHAR(h.horaFin, 'HH24:MI') AS Horario, " +
                       "h.diaSemana AS Día, " +
                       "TO_CHAR(h.fechaInicio, 'YYYY-MM-DD') AS FechaInicio, " +
                       "TO_CHAR(h.fechaFin, 'YYYY-MM-DD') AS FechaFin, " +
                       "u.nombre || ' ' || u.apellido AS Docente, " +
                       "m.vancantes AS Vacantes " +
                       "FROM Horario h " +
                       "JOIN Modulo m ON h.idModulo = m.idModulo " +
                       "JOIN Idioma id ON m.idIdioma = id.idIdioma " +
                       "JOIN AsignacionDocentes ad ON h.idHorario = ad.idHorario " +
                       "JOIN Usuarios u ON ad.idUsuario = u.idUsuario " +
                       "ORDER BY id.idioma, h.horaInicio";
    
        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
    
            while (rs.next()) {
                listaHorarios.add(new HorarioDisponible(
                        rs.getString("Idioma"),
                        rs.getString("Horario"),
                        rs.getString("Día"),
                        rs.getString("FechaInicio"),
                        rs.getString("FechaFin"),
                        rs.getString("Docente"),
                        rs.getInt("Vacantes")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return listaHorarios;
    }
    
}
