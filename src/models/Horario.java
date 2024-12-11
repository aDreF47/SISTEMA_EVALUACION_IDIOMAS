package models;

import java.time.LocalTime;

public class Horario {
    
    private String idHorario;
    private LocalTime horarioInicio; 
    private LocalTime horaFin; 
    private String diaSemana; 
    private int disponible; 

    public Horario() {}

    public Horario(String idHorario, LocalTime horarioInicio, LocalTime horaFin, String diaSemana, int disponible) {
        setIdHorario(idHorario);
        setHorarioInicio(horarioInicio);
        setHoraFin(horaFin);
        setDiaSemana(diaSemana);
        setDisponible(disponible);
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        if (idHorario != null && idHorario.length() <= 5) {
            this.idHorario = idHorario;
        } else {
            throw new IllegalArgumentException("El idHorario debe tener un máximo de 5 caracteres.");
        }
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        if (horarioInicio != null) {
            this.horarioInicio = horarioInicio;
        } else {
            throw new IllegalArgumentException("El horarioInicio no puede ser nulo.");
        }
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        if (horaFin != null && (horarioInicio == null || horaFin.isAfter(horarioInicio))) {
            this.horaFin = horaFin;
        } else {
            throw new IllegalArgumentException("La horaFin debe ser posterior al horarioInicio.");
        }
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana != null && diaSemana.length() <= 25) {
            this.diaSemana = diaSemana;
        } else {
            throw new IllegalArgumentException("El diaSemana debe tener un máximo de 25 caracteres.");
        }
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        if (disponible == 0 || disponible == 1) {
            this.disponible = disponible;
        } else {
            throw new IllegalArgumentException("El disponible debe ser 0 (no disponible) o 1 (disponible).");
        }
    }

    @Override
    public String toString() {
        return "Horario{" +
               "idHorario='" + idHorario + '\'' +
               ", horarioInicio=" + horarioInicio +
               ", horaFin=" + horaFin +
               ", diaSemana='" + diaSemana + '\'' +
               ", disponible=" + disponible +
               '}';
    }
}
