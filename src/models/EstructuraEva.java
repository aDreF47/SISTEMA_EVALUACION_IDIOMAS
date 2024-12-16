package models;

public class EstructuraEva {
    
    private String idEstructura;
    private String tipoExamen;
    private int puntosPorPregunta;
    private int duracion;

    
    
    public EstructuraEva() {}

    public EstructuraEva(String idEstructura, String tipoExamen, int puntosPorPregunta, int duracion) {
        this.idEstructura = idEstructura;
        this.tipoExamen = tipoExamen;
        this.puntosPorPregunta = puntosPorPregunta;
        this.duracion = duracion;
    }
    
    
    public String getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(String idEstructura) {
        if (idEstructura != null && idEstructura.length() <= 2) {
            this.idEstructura = idEstructura;
        } else {
            throw new IllegalArgumentException("El idEstructura debe tener un máximo de 2 caracteres.");
        }
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if (duracion >0) {
            this.duracion = duracion;
        } else {
            throw new IllegalArgumentException("La duración no puede ser nula.");
        }
    }

    public int getPuntosPorPregunta() {
        return puntosPorPregunta;
    }

    public void setPuntosPorPregunta(int puntosPorPregunta) {
        this.puntosPorPregunta = puntosPorPregunta;
    }
    
    
    @Override
    public String toString() {
        return "EstructuraEva{" +
                "idEstructura='" + idEstructura + '\'' +
                ", tipoExamen='" + tipoExamen + '\'' +
                ", puntosPorPregunta=" + puntosPorPregunta +
                ", duracion=" + duracion +
                '}';
    }
 
}
