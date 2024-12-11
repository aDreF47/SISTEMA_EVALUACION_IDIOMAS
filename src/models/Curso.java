
package models;

public class Curso {

    private String idCurso; 
    private String idModulo; 
    private String idHorario; 
    private String nombre; 
    private int vacante; 
    private String estado; 

    public Curso() {}

    public Curso(String idCurso, String idModulo, String idHorario, String nombre, int vacante, String estado) {
        setIdCurso(idCurso);
        setIdModulo(idModulo);
        setIdHorario(idHorario);
        setNombre(nombre);
        setVacante(vacante);
        setEstado(estado);
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        if (idCurso != null && idCurso.length() <= 3) {
            this.idCurso = idCurso;
        } else {
            throw new IllegalArgumentException("El idCurso debe tener un máximo de 3 caracteres.");
        }
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        if (idModulo != null && idModulo.length() <= 8) {
            this.idModulo = idModulo;
        } else {
            throw new IllegalArgumentException("El idModulo debe tener un máximo de 8 caracteres.");
        }
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() <= 50) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre debe tener un máximo de 50 caracteres.");
        }
    }

    public int getVacante() {
        return vacante;
    }

    public void setVacante(int vacante) {
        if (vacante >= 0 && vacante <= 99) {
            this.vacante = vacante;
        } else {
            throw new IllegalArgumentException("El número de vacantes debe estar entre 0 y 99.");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado != null && estado.length() <= 40) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("El estado debe tener un máximo de 40 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Curso{" +
               "idCurso='" + idCurso + '\'' +
               ", idModulo='" + idModulo + '\'' +
               ", idHorario='" + idHorario + '\'' +
               ", nombre='" + nombre + '\'' +
               ", vacante=" + vacante +
               ", estado='" + estado + '\'' +
               '}';
    }
}
