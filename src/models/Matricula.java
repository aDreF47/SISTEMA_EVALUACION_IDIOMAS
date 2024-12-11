
package models;

import java.time.LocalDateTime;

public class Matricula {
    
    private int idMatricula;
    private String idCurso;
    private String idFactura;
    private LocalDateTime fechaMatricula;
    private String estado;

    public Matricula() {}

    public Matricula(int idMatricula, String idCurso, String idFactura, LocalDateTime fechaMatricula, String estado) {
        setIdMatricula(idMatricula);
        setIdCurso(idCurso);
        setIdFactura(idFactura);
        setFechaMatricula(fechaMatricula);
        setEstado(estado);
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula > 0) {
            this.idMatricula = idMatricula;
        } else {
            throw new IllegalArgumentException("El idMatricula debe ser un entero positivo.");
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

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        if (idFactura != null && idFactura.length() == 8) {
            this.idFactura = idFactura;
        } else {
            throw new IllegalArgumentException("El idFactura debe tener exactamente 8 caracteres.");
        }
    }

    public LocalDateTime getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDateTime fechaMatricula) {
        if (fechaMatricula != null) {
            this.fechaMatricula = fechaMatricula;
        } else {
            throw new IllegalArgumentException("La fechaMatricula no puede ser nula.");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado != null && estado.length() <= 40) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("El estado debe tener como máximo 40 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Matricula{" +
               "idMatricula=" + idMatricula +
               ", idCurso='" + idCurso + '\'' +
               ", idFactura='" + idFactura + '\'' +
               ", fechaMatricula=" + fechaMatricula +
               ", estado='" + estado + '\'' +
               '}';
    }  
}
