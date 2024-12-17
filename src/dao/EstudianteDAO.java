
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import models.Estudiante;
import models.Matricula;
import utils.Conexion;

public class EstudianteDAO {

    // Método para generar un código único de 8 dígitos
    private String generarCodigoEstudiante() {
        Random random = new Random();
        String codigo;
        boolean existe;

        do {
            codigo = String.format("%08d", random.nextInt(100000000)); // Genera un número de 8 dígitos
            existe = verificarCodigoExistente(codigo);
        } while (existe);

        return codigo;
    }

    // Método para verificar si el código ya existe en la base de datos
    private boolean verificarCodigoExistente(String codigo) {
        String query = "SELECT COUNT(*) AS total FROM Estudiante WHERE codigo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //recien
    public String buscarEstudiante(int idUsuario) {
        String query = "SELECT codigo FROM Estudiante WHERE idUsuario = ?";
        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Retornar el código del estudiante si ya existe
                return rs.getString("codigo");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Retornar null si no se encuentra el estudiante
        return null;
    }

    public int verificarIdEstudiante(int idUsuario) {
        String query = "SELECT idestudiante FROM Estudiante WHERE idUsuario = ?";
        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Retornar el código del estudiante si ya existe
                return rs.getInt("idEstudiante");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Retornar null si no se encuentra el estudiante
        return -1;
    }

   // Método para registrar un nuevo estudiante
   public boolean insertarEstudiante(Estudiante estudiante) {
       String query = "INSERT INTO Estudiante (idUsuario, codigo, estado) VALUES (?, ?, ?)";
       System.out.println("en insert: "+estudiante.getCodigo());
       try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(query)) {

           ps.setInt(1, estudiante.getIdUsuario());
           ps.setString(2, estudiante.getCodigo());
           ps.setInt(3, estudiante.getEstado());

           int filasInsertadas = ps.executeUpdate();
           return filasInsertadas > 0;

       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }


    public boolean registrarEstudianteYMatricula(int idUsuario, int idModulo) {
        String queryEstudiante = "INSERT INTO Estudiante (idUsuario, codigo, estado) VALUES (?, ?, ?)";
        String queryMatricula = "INSERT INTO Matricula (idEstudiante, idModulo, estado) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement psEstudiante = null;
        PreparedStatement psMatricula = null;
        ResultSet rsGenerado = null;

        try {
            con = Conexion.conectar();
            con.setAutoCommit(false); // Deshabilitar autocommit para manejar transacciones

            // 1. Registrar el estudiante
            String codigoEstudiante = generarCodigoEstudiante();
            psEstudiante = con.prepareStatement(queryEstudiante, new String[] { "idEstudiante" });
            psEstudiante.setInt(1, idUsuario);
            psEstudiante.setString(2, codigoEstudiante);
            psEstudiante.setInt(3, 1); // Estado 1 = Activo

            int filasEstudiante = psEstudiante.executeUpdate();

            if (filasEstudiante == 0) {
                throw new Exception("Error al registrar el estudiante.");
            }

            // Obtener el ID del estudiante recién insertado
            rsGenerado = psEstudiante.getGeneratedKeys();
            int idEstudiante = -1;
            if (rsGenerado.next()) {
                idEstudiante = rsGenerado.getInt(1);
            } else {
                throw new Exception("No se pudo obtener el ID del estudiante.");
            }

            // 2. Registrar la matrícula
            psMatricula = con.prepareStatement(queryMatricula);
            psMatricula.setInt(1, idEstudiante);
            psMatricula.setInt(2, idModulo); // Relación con el módulo
            psMatricula.setInt(3, 1); // Estado 1 = Matriculado

            int filasMatricula = psMatricula.executeUpdate();
            if (filasMatricula == 0) {
                throw new Exception("Error al registrar la matrícula.");
            }

            // Confirmar la transacción
            con.commit();
            System.out.println("Estudiante y matrícula registrados exitosamente. Código: " + codigoEstudiante);
            return true;

        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback(); // Deshacer cambios si algo falla
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rsGenerado != null) rsGenerado.close();
                if (psEstudiante != null) psEstudiante.close();
                if (psMatricula != null) psMatricula.close();
                if (con != null) con.close();
            } catch (Exception closeEx) {
                closeEx.printStackTrace();
            }
        }
        return false;
    }
    
    //Creado reciend
    public int verificarIdEstudiante(String codigo) {
        String query = "SELECT idEstudiante FROM Estudiante WHERE codigo = ?";
        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Retornar el código del estudiante si ya existe
                return rs.getInt("idEstudiante");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Retornar null si no se encuentra el estudiante
        return -1;
    }
    //creado recien
    public boolean insertarMatricula(Matricula matricula) {
        String query = "INSERT INTO Matricula (idAsignacion, idEstudiante, fechaMatricula, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, matricula.getIdAsignacion());
            ps.setInt(2, matricula.getIdEstudiante());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(matricula.getFechaMatricula())); // Convertir LocalDate a Date
            ps.setInt(4, matricula.getEstado());

            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
