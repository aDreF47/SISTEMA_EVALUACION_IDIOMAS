
package models;

public class Rol {
    private String idRol;
    private String nombre;

    public Rol(String idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }

}
