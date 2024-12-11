package models;

import java.time.LocalDateTime;

public class Estudiante {
    
    private int idEstudiante;
    private String codigo;
    private String nombre; 
    private String email; 
    private String contrasena; 
    private LocalDateTime fechaRegistro;
    private String estado;

    public Estudiante() {}

    public Estudiante(int idEstudiante, String codigo, String nombre, String email, String contrasena, LocalDateTime fechaRegistro, String estado) {
        setIdEstudiante(idEstudiante);
        setCodigo(codigo);
        setNombre(nombre);
        setEmail(email);
        setContrasena(contrasena);
        setFechaRegistro(fechaRegistro);
        setEstado(estado);
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        if (idEstudiante > 0) {
            this.idEstudiante = idEstudiante;
        } else {
            throw new IllegalArgumentException("El idEstudiante debe ser un entero positivo.");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && codigo.length() <= 8) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código debe tener un máximo de 8 caracteres.");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() <= 50 && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("El email debe tener un máximo de 50 caracteres y ser válido.");
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena != null && contrasena.length() <= 10) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña debe tener un máximo de 10 caracteres.");
        }
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        if (fechaRegistro != null) {
            this.fechaRegistro = fechaRegistro;
        } else {
            throw new IllegalArgumentException("La fecha de registro no puede ser nula.");
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
        return "Estudiante{" +
               "idEstudiante=" + idEstudiante +
               ", codigo='" + codigo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", contrasena='" + contrasena + '\'' +
               ", fechaRegistro=" + fechaRegistro +
               ", estado='" + estado + '\'' +
               '}';
    }
}
