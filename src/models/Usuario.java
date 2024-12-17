
package models;

import java.time.LocalDateTime;
import java.util.Date;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String usuario;
    private String contrasena;
    private LocalDateTime fechaRegistro;
    private int estado;
    private Rol rol; // Rol asociado al usuario


    public Usuario(){
        this.fechaRegistro = LocalDateTime.now();
        this.estado=1;
    }
    
    public Usuario(int idUsuario, String nombre, String apellido, String dni, String email, String usuario, String contrasena, LocalDateTime fechaRegistro, int estado, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.rol = rol;
    }
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String us) {
        this.usuario = us;
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    

    
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                ", rol=" + rol +
                '}';
    }
    
    // Campo adicional específico
    private String especializacion;

    // Getters y Setters para especializacion
    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
    
    
}
