
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
}
