
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import models.CursoActivo;
import models.HorarioDisponible;

import models.Rol;
import models.Usuario;
import utils.Conexion;


public class UsuarioDAO {
    //private Conexion nc = new Conexion();
    private ArrayList<Usuario> listaEstudiantes = new ArrayList<>();

    public ArrayList<Usuario> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Usuario> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    
    public boolean validateUser(String userName, String password) {
        boolean isValidUser = false;
        String consulta = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";

        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isValidUser = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return isValidUser;
    }
    public boolean validarExistenciaUser(String email) {
        boolean isValidUserName = false;
        String consulta = "SELECT * FROM usuario WHERE email = ?";

        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isValidUserName = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return isValidUserName;
    }
    
    public Usuario validarYObtenerUsuario(String emailOUsuario, String password) {
        Usuario usuario = null;
        String query = """
            SELECT u.*, r.idRol, r.nombre AS nombreRol
            FROM usuarios u
            JOIN UsuarioRol ur ON u.idUsuario = ur.idUsuario
            JOIN Rol r ON ur.idRol = r.idRol
            WHERE (u.email = ? OR u.usuario = ?) AND u.contrasena = ? AND u.estado = 1
        """;

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, emailOUsuario);
            ps.setString(2, emailOUsuario);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setDni(rs.getString("dni"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
                usuario.setEstado(rs.getInt("estado"));

                // Crear el rol asociado
                Rol rol = new Rol(rs.getString("idRol"), rs.getString("nombreRol"));
                usuario.setRol(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

     
    public boolean validateDocumentoUnico(String documento) {
        boolean existeDocumento = false;
        String consulta = "SELECT * FROM Usuarios WHERE  DNI = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                existeDocumento = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return existeDocumento;
    }
    
    public boolean validarCorreoUnico(String email) {
        boolean existeEmail = false;
        String consulta = "SELECT * FROM Usuarios WHERE  email = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            System.out.println(email);

            if (rs.next()) {
                existeEmail = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return existeEmail;
    }    
    
    public boolean validarUsuarioUnico(String email) {
        String query = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Usuario ya existe en la base de datos
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Usuario no existe en la base de datos
    }
    
    public static ObservableList<Usuario> obtenerDocentes() {
        ObservableList<Usuario> listaDocentes = FXCollections.observableArrayList();
        String query = """
            SELECT u.idUsuario, u.nombre, u.apellido, du.valor AS especializacion
            FROM usuarios u
            JOIN UsuarioRol ur ON u.idUsuario = ur.idUsuario
            JOIN DetalleUsuario du ON u.idUsuario = du.idUsuario
            WHERE ur.idRol = 2 AND du.clave = 'especializacion'
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Usuario docente = new Usuario();
                docente.setIdUsuario(rs.getInt("idUsuario"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setEspecializacion(rs.getString("especializacion"));
                listaDocentes.add(docente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDocentes;
    }
    
    public static boolean crearModulo(String idIdioma, String nombre, int numeroModulo, int vacantes) {
        String sql = """
            INSERT INTO MODULO (idIdioma, nombre, numeroModulo, vancantes, estado) 
            VALUES (?, ?, ?, ?, 1)
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            // Asignar parámetros
            pst.setString(1, idIdioma);
            pst.setString(2, nombre);
            pst.setInt(3, numeroModulo);
            pst.setInt(4, vacantes);

            // Ejecutar la consulta
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean crearHorario(int idModulo, String horaInicio, String horaFin, String diaSemana, String fechaInicio, String fechaFin) {
        String sql = """
            INSERT INTO Horario (idModulo, horaInicio, horaFin, diaSemana, fechaInicio, fechaFin) 
            VALUES (?, TO_TIMESTAMP(?, 'HH24:MI:SS'), TO_TIMESTAMP(?, 'HH24:MI:SS'), ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'))
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            // Asignar parámetros
            pst.setInt(1, idModulo);
            pst.setString(2, horaInicio);
            pst.setString(3, horaFin);
            pst.setString(4, diaSemana);
            pst.setString(5, fechaInicio);
            pst.setString(6, fechaFin);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;  // Devuelve true si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    
    
    public static int obtenerUltimoIdModulo() {
        String sql = "SELECT MAX(idModulo) AS ultimoId FROM MODULO";
        int ultimoId = 0;

        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                ultimoId = rs.getInt("ultimoId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ultimoId;
    }
    
    public static boolean crearAsignacionDocente(int idHorario, int idUsuario) {
        String query = "INSERT INTO AsignacionDocentes (idHorario, idUsuario) VALUES (?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, idHorario);
            pst.setInt(2, idUsuario);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static int obtenerUltimoIdHorario() {
        String query = "SELECT MAX(idHorario) AS idHorario FROM Horario";
        int idHorario = 0;

        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                idHorario = rs.getInt("idHorario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idHorario;
    }
    
    public static ObservableList<CursoActivo> obtenerCursosActivos() {
        ObservableList<CursoActivo> listaCursos = FXCollections.observableArrayList();
        String query = "SELECT h.idHorario, " +
                       "m.NOMBRE || ' ' || m.NUMEROMODULO AS Idioma, " +
                       "TO_CHAR(h.horaInicio, 'HH24:MI') || ' - ' || TO_CHAR(h.horaFin, 'HH24:MI') AS Horario, " +
                       "h.diaSemana AS Día, " +
                       "TO_CHAR(h.fechaInicio, 'YYYY-MM-DD') AS FechaInicio, " +
                       "TO_CHAR(h.fechaFin, 'YYYY-MM-DD') AS FechaFin, " +
                       "u.nombre || ' ' || u.apellido AS Docente, " +
                       "m.vancantes AS Vacantes " +
                       "FROM Horario h " +
                       "JOIN Modulo m ON h.idModulo = m.idModulo " +
                       "JOIN AsignacionDocentes ad ON h.idHorario = ad.idHorario " +
                       "JOIN Usuarios u ON ad.idUsuario = u.idUsuario " +
                       "ORDER BY m.NOMBRE, h.horaInicio";

        try (Connection con = Conexion.conectar();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                listaCursos.add(new CursoActivo(
                    rs.getInt("idHorario"), // Nuevo campo
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

        return listaCursos;
    }

    public boolean registrarCliente(Usuario cliente) {
        String insertUsuario = "INSERT INTO usuarios (nombre, apellido, dni, email, usuario, contrasena, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String selectIdUsuario = "SELECT idUsuario FROM usuarios WHERE dni = ?";
        String insertUsuarioRol = "INSERT INTO usuarioRol (idRol, idUsuario) VALUES (?, ?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            // Insertar en la tabla usuarios
            try (PreparedStatement psUsuario = con.prepareStatement(insertUsuario)) {
                psUsuario.setString(1, cliente.getNombre());
                psUsuario.setString(2, cliente.getApellido());
                psUsuario.setString(3, cliente.getDni());
                psUsuario.setString(4, cliente.getEmail());
                psUsuario.setString(5, cliente.getUsuario());
                psUsuario.setString(6, cliente.getContrasena());
                psUsuario.setInt(7, cliente.getEstado());
                psUsuario.executeUpdate();
            }
            // Obtener idUsuario basado en el DNI
            int idUsuario = -1;
            try (PreparedStatement psSelectId = con.prepareStatement(selectIdUsuario)) {
                psSelectId.setString(1, cliente.getDni());
                try (ResultSet rs = psSelectId.executeQuery()) {
                    if (rs.next()) {
                        idUsuario = rs.getInt("idUsuario");
                    } else {
                        throw new SQLException("No se encontró el idUsuario para el DNI proporcionado.");
                    }
                }
            }

            // Insertar en la tabla usuarioRol
            try (PreparedStatement psUsuarioRol = con.prepareStatement(insertUsuarioRol)) {
                psUsuarioRol.setInt(1, 3); // idRol = 3 para "Estudiante"
                psUsuarioRol.setInt(2, idUsuario);
                psUsuarioRol.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int validarRolUsuario(String dni) {
    String consultaUsuario = "SELECT idUsuario FROM usuarios WHERE dni = ?";
    String consultaRol = "SELECT idRol FROM usuarioRol WHERE idUsuario = ?";

    try (Connection con = Conexion.conectar();
         PreparedStatement psUsuario = con.prepareStatement(consultaUsuario);
         PreparedStatement psRol = con.prepareStatement(consultaRol)) {
        
        // Buscar el idUsuario por dni
        psUsuario.setString(1, dni);
        ResultSet rsUsuario = psUsuario.executeQuery();
        if (rsUsuario.next()) {
            int idUsuario = rsUsuario.getInt("idUsuario");
            rsUsuario.close();
            
            // Buscar el idRol por idUsuario
            psRol.setInt(1, idUsuario);
            ResultSet rsRol = psRol.executeQuery();
            if (rsRol.next()) {
                int idRol = rsRol.getInt("idRol");
                rsRol.close();
                return idRol;
            }
            rsRol.close();
        }
        rsUsuario.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Retorna -1 si ocurre un error o no encuentra el rol
}


}


