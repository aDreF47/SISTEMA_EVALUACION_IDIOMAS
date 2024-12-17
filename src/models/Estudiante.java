package models;


public class Estudiante {
    
    private int idEstudiante;
    private int idUsuario;
    private String codigo;
    private int estado;

    public Estudiante() {}

    public Estudiante(int idEstudiante, int idUsuario, String codigo, int estado) {
        this.idEstudiante = idEstudiante;
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.estado = estado;
    }
    public Estudiante(int idUsuario, String codigo, int estado) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.estado = estado;
    }

    public Estudiante(int idUsuario, String codigo, int estado) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.estado = estado;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

      
}
