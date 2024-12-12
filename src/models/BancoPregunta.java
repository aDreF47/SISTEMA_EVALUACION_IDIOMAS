package models;

import java.util.List;

public class BancoPregunta {
    
    private int idPregunta; 
    private String idModulo; 
    private String contenido; 
    private List<String> alternativas; 
    private String respuestaCorrecta; 

    public BancoPregunta() {}

    public BancoPregunta(int idPregunta, String idModulo, String contenido, List<String> alternativas, String respuestaCorrecta) {
        this.idPregunta = idPregunta;
        this.idModulo = idModulo;
        this.contenido = contenido;
        this.alternativas = alternativas;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

   
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
               "idPregunta=" + idPregunta +
               ", idModulo='" + idModulo + '\'' +
               ", contenido='" + contenido + '\'' +
               ", alternativas=" + alternativas +
               ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
               '}';
    }
}
