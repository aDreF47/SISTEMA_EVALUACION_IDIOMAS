package models;

import java.time.LocalDateTime;

public class Nota {
    
    private int idNota;
    private int idExamen;
    private int idEstudiante;
    private double puntaje;
    private LocalDateTime fechaCompletado;

    public Nota() {}

    public Nota(int idNota, int idExamen, int idEstudiante, double puntaje, LocalDateTime fechaCompletado) {
        setIdNota(idNota);
        setIdExamen(idExamen);
        setIdEstudiante(idEstudiante);
        setPuntaje(puntaje);
        setFechaCompletado(fechaCompletado);
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        if (idNota > 0) {
            this.idNota = idNota;
        } else {
            throw new IllegalArgumentException("El idNota debe ser un entero positivo.");
        }
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        if (idExamen > 0) {
            this.idExamen = idExamen;
        } else {
            throw new IllegalArgumentException("El idExamen debe ser un entero positivo.");
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

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        if (puntaje >= 0.0 && puntaje <= 99.9) { 
            this.puntaje = Math.round(puntaje * 10.0) / 10.0;
        } else {
            throw new IllegalArgumentException("El puntaje debe estar entre 0.0 y 99.9, con 1 decimal de precisión.");
        }
    }

    public LocalDateTime getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(LocalDateTime fechaCompletado) {
        if (fechaCompletado != null) {
            this.fechaCompletado = fechaCompletado;
        } else {
            throw new IllegalArgumentException("La fechaCompletado no puede ser nula.");
        }
    }

    @Override
    public String toString() {
        return "Nota{" +
               "idNota=" + idNota +
               ", idExamen=" + idExamen +
               ", idEstudiante=" + idEstudiante +
               ", puntaje=" + puntaje +
               ", fechaCompletado=" + fechaCompletado +
               '}';
    }
}
