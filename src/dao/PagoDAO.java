/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import models.Pago;
import utils.Conexion;

public class PagoDAO {

    public boolean insertarPago(Pago pago) {
        String query = "INSERT INTO Pago (idUsuario, idMatricula, monto, fechaPago, descripcion, codPago, estado) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {
    
            ps.setInt(1, pago.getIdUsuario());
            ps.setString(2, pago.getIdMatricula());
            ps.setDouble(3, pago.getMonto());
            ps.setDate(4, pago.getFechaPago());
            ps.setString(5, pago.getDescripcion());
            ps.setString(6, pago.getCodPago());
            ps.setInt(7, pago.getEstado());
    
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean validarYUsarCodigoPago(String codPago, int idMatricula) {
        String queryValidar = "SELECT estado FROM Pago WHERE codPago = ?";
    
        String queryUsar = "UPDATE Pago SET estado = 1, idMatricula = ? WHERE codPago = ? AND estado = 0";
    
        try (Connection con = Conexion.conectar();
             PreparedStatement psValidar = con.prepareStatement(queryValidar);
             PreparedStatement psUsar = con.prepareStatement(queryUsar)) {
    
            // Verificar si el código de pago existe y no está usado
            psValidar.setString(1, codPago);
            ResultSet rs = psValidar.executeQuery();
    
            if (rs.next()) {
                int estado = rs.getInt("estado");
                if (estado == 1) {
                    // El código ya fue usado
                    return false;
                }
            } else {
                // El código no existe
                return false;
            }
    
            // Actualizar el código de pago como usado y asociarlo a la matrícula
            psUsar.setInt(1, idMatricula);
            psUsar.setString(2, codPago);
    
            int filasActualizadas = psUsar.executeUpdate();
            return filasActualizadas > 0; // Si se actualizó al menos una fila, fue exitoso
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar si existe el registro del pago
    public boolean verificarRegistroPago(String codPago, String fechaPago) {
        String query = "SELECT COUNT(*) AS total FROM Pago WHERE CODPAGO = ? AND TRUNC(FECHAPAGO) = TRUNC(TO_TIMESTAMP(?, 'YYYY-MM-DD'))";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {
    
            ps.setString(1, codPago);
            ps.setString(2, fechaPago);
    
            System.out.println("Consulta SQL: " + query);
            System.out.println("Código de pago: " + codPago);
            System.out.println("Fecha enviada: " + fechaPago);
    
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("hola si existe el pago en registro");
                System.out.println(rs.getInt("total"));
                return rs.getInt("total") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    // Verificar si el pago está disponible (ESTADO = 0)
    public boolean verificarEstadoPago(String codPago, String fechaPago) {
        String query = "SELECT ESTADO FROM Pago WHERE CODPAGO = ? AND TRUNC(FECHAPAGO) = TO_TIMESTAMP(?, 'YYYY-MM-DD')";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, codPago);
            ps.setString(2, fechaPago);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("hola si existe el pago, SU ESTADO ES: " + rs.getInt("ESTADO") );
                return rs.getInt("ESTADO") == 0; // Estado = 0 significa no usado
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
