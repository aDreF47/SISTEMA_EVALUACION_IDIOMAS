
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Usuario;
import utils.Conexion;

/**
 *
 * @author JuniorG
 */
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
    public Usuario validarYObtenerUsuario(String email, String password) {
        Usuario usuario = null;
        String query = "SELECT id_usuario, email, contrasena FROM usuario WHERE email = ? AND contrasena = ?";
        
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario() {};
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("contrasena"));
                usuario.setEstado(rs.getInt("estado"));
                
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
