package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Examen;
import utils.Conexion;

public class ExamenDAO {

    public Examen obtenerExamenPorId(int idExamen) {
        Examen examen = null;
        String query = """
            SELECT idExamen, idEstructura, idModulo, fechaExamen
            FROM Examen
            WHERE idExamen = ?
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idExamen);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                examen = new Examen(
                        rs.getInt("idExamen"),
                        rs.getString("idEstructura"),
                        rs.getString("idModulo"),
                        rs.getTimestamp("fechaExamen").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return examen;
    }

    public List<Examen> listarExamenes() {
        List<Examen> examenes = new ArrayList<>();
        String query = """
            SELECT idExamen, idEstructura, idModulo, fechaExamen
            FROM Examen
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Examen examen = new Examen(
                        rs.getInt("idExamen"),
                        rs.getString("idEstructura"),
                        rs.getString("idModulo"),
                        rs.getTimestamp("fechaExamen").toLocalDateTime()
                );
                examenes.add(examen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return examenes;
    }
}

