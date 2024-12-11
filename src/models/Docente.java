package models;

public class Docente {
    
    private short idDocente;
    private String codigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String especializacion;

    public Docente() {}

    public Docente(short idProfesor, String codigo, String nombre, String apellidoPaterno, 
                    String apellidoMaterno, String email, String especializacion) {
        setIdDocente(idProfesor);
        setCodigo(codigo);
        setNombre(nombre);
        setApellidoPaterno(apellidoPaterno);
        setApellidoMaterno(apellidoMaterno);
        setEmail(email);
        setEspecializacion(especializacion);
    }

    public short getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(short idDocente) {
        if (idDocente > 0) {
            this.idDocente = idDocente;
        } else {
            throw new IllegalArgumentException("El idProfesor debe ser un valor positivo.");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && codigo.length() <= 10) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código debe tener como máximo 10 caracteres.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() <= 50) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre debe tener como máximo 50 caracteres.");
        }
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        if (apellidoPaterno != null && apellidoPaterno.length() <= 50) {
            this.apellidoPaterno = apellidoPaterno;
        } else {
            throw new IllegalArgumentException("El apellido paterno debe tener como máximo 50 caracteres.");
        }
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        if (apellidoMaterno != null && apellidoMaterno.length() <= 50) {
            this.apellidoMaterno = apellidoMaterno;
        } else {
            throw new IllegalArgumentException("El apellido materno debe tener como máximo 50 caracteres.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() <= 50) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("El email debe tener como máximo 50 caracteres.");
        }
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        if (especializacion != null && especializacion.length() <= 40) {
            this.especializacion = especializacion;
        } else {
            throw new IllegalArgumentException("La especialización debe tener como máximo 40 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Profesor{" +
               "idProfesor=" + idDocente +
               ", codigo='" + codigo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", apellidoPaterno='" + apellidoPaterno + '\'' +
               ", apellidoMaterno='" + apellidoMaterno + '\'' +
               ", email='" + email + '\'' +
               ", especializacion='" + especializacion + '\'' +
               '}';
    } 
}
