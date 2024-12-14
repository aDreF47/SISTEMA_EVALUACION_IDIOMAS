package models;

import java.sql.Date;

public class Pago {
    private int idPago;
    private int idUsuario;
    private String idMatricula;
    private double monto;
    private Date fechaPago;
    private String descripcion;
    private String codPago;
    private int estado;

    // Constructor completo
    public Pago(int idUsuario, String idMatricula, double monto, Date fechaPago, String descripcion, String codPago, int estado) {
        this.idUsuario = idUsuario;
        this.idMatricula = idMatricula;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.descripcion = descripcion;
        this.codPago = codPago;
        this.estado = estado;
    }

    // Getters y setters...

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodPago() {
        return codPago;
    }

    public void setCodPago(String codPago) {
        this.codPago = codPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
