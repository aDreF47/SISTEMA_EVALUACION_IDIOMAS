
package models;

import java.time.LocalDateTime;

public class Matricula {
    
    private int idMatricula;
    private int idEstudiante;
    private int idAsignacion;
    private LocalDateTime fechaMatricula;
    private int estado;

    public Matricula() {
        this.fechaMatricula = LocalDateTime.now();
    }

    public Matricula(int idMatricula, int idAsignacion, int idEstudiante, LocalDateTime fechaMatricula, int estado) {
        this.idMatricula = idMatricula;
        this.idAsignacion = idAsignacion;
        this.idEstudiante = idEstudiante;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    public Matricula(int idAsignacion, int idEstudiante, LocalDateTime fechaMatricula, int estado) {
        this.idAsignacion = idAsignacion;
        this.idEstudiante = idEstudiante;
        this.idAsignacion = idAsignacion;
        this.estado = estado;
        this.fechaMatricula = LocalDateTime.now();
    }
    
    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int idAsignacion() {
        return idAsignacion;
    }

    public void setIdModulo(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }


    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
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
