package models;

import java.time.LocalDateTime;

public class Modulo {
    
    private String idModulo;
    private String idIdioma;
    private String nombre;
    private int numModulo;
    private int vacantes;
    private int estado;
    private LocalDateTime fechaRegistro;

    public Modulo() {}

    public Modulo(String idModulo, String idIdioma, String nombre, int numModulo, int vacantes, int estado, LocalDateTime fechaRegistro) {
        this.idModulo = idModulo;
        this.idIdioma = idIdioma;
        this.nombre = nombre;
        this.numModulo = numModulo;
        this.vacantes = vacantes;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumModulo() {
        return numModulo;
    }

    public void setNumModulo(int numModulo) {
        this.numModulo = numModulo;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    
}
