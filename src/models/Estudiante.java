package models;


public class Estudiante {
    
    private int idEstudiante;
    private int idUsuario;
    private String codigo;
    private boolean estado;

    public Estudiante() {}

    public Estudiante(int idEstudiante, int idUsuario, String codigo, boolean estado) {
        this.idEstudiante = idEstudiante;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

      
}
