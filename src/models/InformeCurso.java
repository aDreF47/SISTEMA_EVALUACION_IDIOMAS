package models;

import java.time.LocalDateTime;

public class InformeCurso {

    private int idInforme;
    private String idModulo;
    private LocalDateTime fechaGeneracion;
    private double puntajePromedio;
    private int totalEstudiantes;
    private double porcentajeAprobados;

    public InformeCurso() {}

    public InformeCurso(int idInforme, String idModulo, LocalDateTime fechaGeneracion, double puntajePromedio, int totalEstudiantes, double porcentajeAprobados) {
        this.idInforme = idInforme;
        this.idModulo = idModulo;
        this.fechaGeneracion = fechaGeneracion;
        this.puntajePromedio = puntajePromedio;
        this.totalEstudiantes = totalEstudiantes;
        this.porcentajeAprobados = porcentajeAprobados;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public double getPuntajePromedio() {
        return puntajePromedio;
    }

    public void setPuntajePromedio(double puntajePromedio) {
        this.puntajePromedio = puntajePromedio;
    }

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        this.totalEstudiantes = totalEstudiantes;
    }

    public double getPorcentajeAprobados() {
        return porcentajeAprobados;
    }

    public void setPorcentajeAprobados(double porcentajeAprobados) {
        this.porcentajeAprobados = porcentajeAprobados;
    }

    
}
