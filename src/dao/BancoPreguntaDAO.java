package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.BancoPregunta;
import utils.Conexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.BancoPregunta;
import utils.Conexion;

import models.BancoPregunta;
import utils.Conexion;

public class BancoPreguntaDAO {
    public ObservableList<BancoPregunta> obtenerBancoPregunta() {
        ObservableList<BancoPregunta> listaPreguntas = FXCollections.observableArrayList();
    
        String query = "SELECT bp.idPregunta, " + 
                       "i.Idioma || ' ' || m.nombre || ' ' || m.numeroModulo AS Modulo, " +
                       "bp.contenido, " +
                       "bp.alternativa1, bp.alternativa2, bp.alternativa3, bp.alternativa4, " +
                       "bp.respuestaCorrecta " +
                       "FROM banco_pregunta bp " +
                       "JOIN MODULO m ON bp.idModulo = m.idModulo " + 
                        "JOIN IDIOMA i ON m.idIdioma = i.idIdioma";
                       

    
        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
    
            while (rs.next()) {
                //listaPregunta.add(new BancoPregunta(
                BancoPregunta pregunta = new BancoPregunta(
                        rs.getInt("idPregunta"),
                        rs.getString("Modulo"),
                        rs.getString("contenido"),
                        rs.getString("alternativa1"),
                        rs.getString("alternativa2"),
                        rs.getString("alternativa3"),
                        rs.getString("alternativa4"),
                        rs.getString("respuestaCorrecta")
                );
                listaPreguntas.add(pregunta);
                //));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return listaPreguntas;
    }
    
    public void insertarPregunta(String idModulo, String contenido, String alternativa1,
                                 String alternativa2, String alternativa3, String alternativa4,
                                 String respuestaCorrecta, String retroalimentacion) {
        String sql = "{CALL insertar_pregunta_banco(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = Conexion.conectar();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, Integer.parseInt(idModulo));
            cs.setString(2, contenido);
            cs.setString(3, alternativa1);
            cs.setString(4, alternativa2);
            cs.setString(5, alternativa3);
            cs.setString(6, alternativa4);
            cs.setString(7, respuestaCorrecta);
            cs.setString(8, retroalimentacion);

            cs.execute();
            System.out.println("Pregunta insertada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al insertar la pregunta: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void eliminarPregunta(int idPregunta) {
    String sql = "DELETE FROM banco_pregunta WHERE idPregunta = ?";

    try (Connection con = Conexion.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, idPregunta);
        int filasAfectadas = pst.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Pregunta eliminada correctamente.");
        } else {
            System.out.println("No se encontró la pregunta con ID: " + idPregunta);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



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
