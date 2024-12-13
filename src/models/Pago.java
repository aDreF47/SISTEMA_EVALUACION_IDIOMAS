package models;

import java.time.LocalDateTime;

public class Pago {
    
    private String idPago;
    private int idEstudiante;
    private String idMatricula;
    private double monto;
    private LocalDateTime fechaPago;
    private String descripcion;
    private String codigoPago;
    private int estado;
    
    public Pago() {}

    public Pago(String idPago, int idEstudiante, String idMatricula, double monto, LocalDateTime fechaPago, String descripcion, String codigoPago, int estado) {
        this.idPago = idPago;
        this.idEstudiante = idEstudiante;
        this.idMatricula = idMatricula;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.descripcion = descripcion;
        this.codigoPago = codigoPago;
        this.estado = estado;
    }

    

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        if (idPago != null && idPago.length() <= 8) {
            this.idPago = idPago;
        } else {
            throw new IllegalArgumentException("El idPago debe tener como máximo 8 caracteres.");
        }
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        if (idEstudiante > 0) {
            this.idEstudiante = idEstudiante;
        } else {
            throw new IllegalArgumentException("El idEstudiante debe ser un entero positivo.");
        }
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (monto >= 0.0 && monto <= 99.99) {
            this.monto = Math.round(monto * 100.0) / 100.0;
        } else {
            throw new IllegalArgumentException("El monto debe estar entre 0.00 y 99.99, con 2 decimales de precisión.");
        }
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        if (fechaPago != null) {
            this.fechaPago = fechaPago;
        } else {
            throw new IllegalArgumentException("La fechaPago no puede ser nula.");
        }
    }

    public String getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(String codigoPago) {
        this.codigoPago = codigoPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

       
    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
