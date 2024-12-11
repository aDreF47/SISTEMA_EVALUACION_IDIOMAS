package models;

import java.time.LocalTime;

public class EstructuraEva {
    
    private String idEstructura;
    private short cantidad;
    private float puntaje;
    private LocalTime duracion; 

    
    public EstructuraEva() {}

    public EstructuraEva(String idEstructura, short cantidad, float puntaje, LocalTime duracion) {
        setIdEstructura(idEstructura);
        setCantidad(cantidad);
        setPuntaje(puntaje);
        setDuracion(duracion);
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

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
        }
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        if (puntaje >= 0 && puntaje <= 99.9) {
            this.puntaje = puntaje;
        } else {
            throw new IllegalArgumentException("El puntaje debe estar entre 0.0 y 99.9.");
        }
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        if (duracion != null) {
            this.duracion = duracion;
        } else {
            throw new IllegalArgumentException("La duración no puede ser nula.");
        }
    }

    @Override
    public String toString() {
        return "EstructuraEva{" +
               "idEstructura='" + idEstructura + '\'' +
               ", cantidad=" + cantidad +
               ", puntaje=" + puntaje +
               ", duracion=" + duracion +
               '}';
    }    
}
