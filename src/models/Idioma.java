
package models;


public class Idioma {
    private String idIdioma;
    private String idioma;
    private int estado;

    public Idioma(String idIdioma, String idioma, int estado) {
        this.idIdioma = idIdioma;
        this.idioma = idioma;
        this.estado = estado;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
