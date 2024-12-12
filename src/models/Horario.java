package models;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {
    
    private String idHorario;
    private String idModulo;
    private LocalTime horarioInicio; 
    private LocalTime horaFin; 
    private String diaSemana;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    

    public Horario() {}

    public Horario(String idHorario, String idModulo, LocalTime horarioInicio, LocalTime horaFin, String diaSemana, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idHorario = idHorario;
        this.idModulo = idModulo;
        this.horarioInicio = horarioInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    
}
