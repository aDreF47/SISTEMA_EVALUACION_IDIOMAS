package models;

import java.time.LocalDateTime;

public class Examen {

    private int idExamen; 
    private String idEstructura; 
    private LocalDateTime fechaExamen;


    public Examen() {}

    public Examen(int idExamen, String idEstructura, LocalDateTime fechaExamen) {
        setIdExamen(idExamen);
        setIdEstructura(idEstructura);
        setFechaExamen(fechaExamen);
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

    public String getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(String idEstructura) {
        if (idEstructura != null && idEstructura.length() <= 5) {
            this.idEstructura = idEstructura;
        } else {
            throw new IllegalArgumentException("El idEstructura debe tener un máximo de 5 caracteres.");
        }
    }

    public LocalDateTime getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(LocalDateTime fechaExamen) {
        if (fechaExamen != null) {
            this.fechaExamen = fechaExamen;
        } else {
            throw new IllegalArgumentException("La fechaExamen no puede ser nula.");
        }
    }

    @Override
    public String toString() {
        return "Examen{" +
               "idExamen=" + idExamen +
               ", idEstructura='" + idEstructura + '\'' +
               ", fechaExamen=" + fechaExamen +
               '}';
    }
}
