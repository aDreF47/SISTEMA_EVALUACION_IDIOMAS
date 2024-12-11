package models;

public class Modulo {
    
    private String idModulo;
    private int nombre;

    public Modulo() {}

    public Modulo(String idModulo, int nombre) {
        setIdModulo(idModulo);
        setNombre(nombre);
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        if (idModulo != null && idModulo.length() == 8) {
            this.idModulo = idModulo;
        } else {
            throw new IllegalArgumentException("El idModulo debe tener exactamente 8 caracteres.");
        }
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        if (nombre >= 0) { 
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre debe ser un valor entero no negativo.");
        }
    }

    @Override
    public String toString() {
        return "Modulo{" +
               "idModulo='" + idModulo + '\'' +
               ", nombre=" + nombre +
               '}';
    }
}
