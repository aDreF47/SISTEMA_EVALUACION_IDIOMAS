package models;

import java.time.LocalDateTime;

public class InformeCurso {

    private int idInforme;
    private String idCurso;
    private LocalDateTime fechaGeneracion;
    private double puntajePromedio;
    private int totalEstudiantes;
    private double tasaCompletitud;

    public InformeCurso() {}

    public InformeCurso(int idInforme, String idCurso, LocalDateTime fechaGeneracion, double puntajePromedio, int totalEstudiantes, double tasaCompletitud) {
        setIdInforme(idInforme);
        setIdCurso(idCurso);
        setFechaGeneracion(fechaGeneracion);
        setPuntajePromedio(puntajePromedio);
        setTotalEstudiantes(totalEstudiantes);
        setTasaCompletitud(tasaCompletitud);
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        if (idInforme > 0) {
            this.idInforme = idInforme;
        } else {
            throw new IllegalArgumentException("El idInforme debe ser un entero positivo.");
        }
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        if (idCurso != null && idCurso.length() == 3) {
            this.idCurso = idCurso;
        } else {
            throw new IllegalArgumentException("El idCurso debe tener exactamente 3 caracteres.");
        }
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        if (fechaGeneracion != null) {
            this.fechaGeneracion = fechaGeneracion;
        } else {
            throw new IllegalArgumentException("La fechaGeneracion no puede ser nula.");
        }
    }

    public double getPuntajePromedio() {
        return puntajePromedio;
    }

    public void setPuntajePromedio(double puntajePromedio) {
        if (puntajePromedio >= 0 && puntajePromedio <= 99.9) {
            this.puntajePromedio = Math.round(puntajePromedio * 10.0) / 10.0;
        } else {
            throw new IllegalArgumentException("El puntajePromedio debe estar entre 0 y 99.9.");
        }
    }

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        if (totalEstudiantes >= 0) {
            this.totalEstudiantes = totalEstudiantes;
        } else {
            throw new IllegalArgumentException("El totalEstudiantes no puede ser negativo.");
        }
    }

    public double getTasaCompletitud() {
        return tasaCompletitud;
    }

    public void setTasaCompletitud(double tasaCompletitud) {
        if (tasaCompletitud >= 0 && tasaCompletitud <= 100.0) {
            this.tasaCompletitud = Math.round(tasaCompletitud * 10.0) / 10.0; 
        } else {
            throw new IllegalArgumentException("La tasaCompletitud debe estar entre 0 y 100.0.");
        }
    }

    @Override
    public String toString() {
        return "Informe_Curso{" +
               "idInforme=" + idInforme +
               ", idCurso='" + idCurso + '\'' +
               ", fechaGeneracion=" + fechaGeneracion +
               ", puntajePromedio=" + puntajePromedio +
               ", totalEstudiantes=" + totalEstudiantes +
               ", tasaCompletitud=" + tasaCompletitud +
               '}';
    }
}
