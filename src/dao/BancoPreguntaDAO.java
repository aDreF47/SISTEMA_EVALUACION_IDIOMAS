package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.BancoPregunta;
import utils.Conexion;

public class BancoPreguntaDAO {

    public List<BancoPregunta> obtenerPreguntasPorExamen(int idExamen) {
    List<BancoPregunta> preguntas = new ArrayList<>();
    String query = """
        SELECT bp.idPregunta, bp.idModulo, bp.contenido, bp.alternativa1, bp.alternativa2, 
               bp.alternativa3, bp.alternativa4, bp.respuestaCorrecta, bp.retroalimentacion
        FROM banco_pregunta bp
        JOIN Examen_BancoPreguntas ebp ON bp.idPregunta = ebp.idPregunta
        WHERE ebp.idExamen = ?
    """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setInt(1, idExamen); // Filtra por el id del examen
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            BancoPregunta pregunta = new BancoPregunta(
                    rs.getInt("idPregunta"),
                    rs.getString("idModulo"),
                    rs.getString("contenido"),
                    rs.getString("alternativa1"),
                    rs.getString("alternativa2"),
                    rs.getString("alternativa3"),
                    rs.getString("alternativa4"),
                    rs.getString("respuestaCorrecta"),
                    rs.getString("retroalimentacion")
            );
            preguntas.add(pregunta);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return preguntas;
}


}
