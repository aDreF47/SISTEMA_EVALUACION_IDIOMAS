
package models;

import java.time.LocalDateTime;

public class Matricula {
    
    private int idMatricula;
    private String idModulo;
    private int idEstudiante;
    private LocalDateTime fechaMatricula;
    private int estado;

    public Matricula() {}

    public Matricula(int idMatricula, String idModulo, int idEstudiante, LocalDateTime fechaMatricula, int estado) {
        this.idMatricula = idMatricula;
        this.idModulo = idModulo;
        this.idEstudiante = idEstudiante;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public LocalDateTime getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDateTime fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
}
