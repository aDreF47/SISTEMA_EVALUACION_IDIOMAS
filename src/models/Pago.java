package models;

import java.time.LocalDateTime;

public class Pago {
    
    private String idPago;
    private int idEstudiante;
    private double monto;
    private LocalDateTime fechaPago;
    private String estado;
    
    public Pago() {}

    public Pago(String idPago, int idEstudiante, double monto, LocalDateTime fechaPago, String estado) {
        setIdPago(idPago);
        setIdEstudiante(idEstudiante);
        setMonto(monto);
        setFechaPago(fechaPago);
        setEstado(estado);
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado != null && estado.length() <= 40) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("El estado debe tener como máximo 40 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Pago{" +
               "idPago='" + idPago + '\'' +
               ", idEstudiante=" + idEstudiante +
               ", monto=" + monto +
               ", fechaPago=" + fechaPago +
               ", estado='" + estado + '\'' +
               '}';
    }
}
