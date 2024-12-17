package models;

import java.time.LocalDateTime;

public class InformeCurso {
    private int idModulo;
    private String fechaGeneracionInforme;
    private double puntajePromedio;
    private int totalEstudiantes;
    private double porcentajeAprobados;

    // Constructor
    public InformeCurso(int idModulo, String fechaGeneracionInforme, double puntajePromedio,
                        int totalEstudiantes, double porcentajeAprobados) {
        this.idModulo = idModulo;
        this.fechaGeneracionInforme = fechaGeneracionInforme;
        this.puntajePromedio = puntajePromedio;
        this.totalEstudiantes = totalEstudiantes;
        this.porcentajeAprobados = porcentajeAprobados;
    }

    // Getters
    public int getIdModulo() { return idModulo; }

    public String getFechaGeneracionInforme() { return fechaGeneracionInforme; }

    public double getPuntajePromedio() { return puntajePromedio; }

    public int getTotalEstudiantes() { return totalEstudiantes; }

    public double getPorcentajeAprobados() { return porcentajeAprobados; }
}
