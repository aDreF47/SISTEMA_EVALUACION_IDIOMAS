/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author JuniorG
 */
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
