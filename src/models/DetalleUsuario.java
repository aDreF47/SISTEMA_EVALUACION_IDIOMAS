
package models;

/**
 *
 * @author JuniorG
 */
public class DetalleUsuario {
    private int idUsuario;
    private String clave;
    private String valor;

    public DetalleUsuario(int idUsuario, String clave, String valor) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.valor = valor;
    }

    
    public int getIdUsuario() {
        return idUsuario;
    }

    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
   
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

        public String getValor() {
        return valor;
    }

  
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
