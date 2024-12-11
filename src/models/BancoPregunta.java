package models;

import java.util.List;

public class BancoPregunta {
    
    private int idPregunta; 
    private String idCurso; 
    private String contenido; 
    private List<String> alternativas; 
    private String respuestaCorrecta; 

    public BancoPregunta() {}

    public BancoPregunta(int idPregunta, String idCurso, String contenido, List<String> alternativas, String respuestaCorrecta) {
        this.idPregunta = idPregunta;
        setIdCurso(idCurso); 
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

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        if (idCurso != null && idCurso.length() <= 3) {
            this.idCurso = idCurso;
        } else {
            throw new IllegalArgumentException("El idCurso debe ser una cadena de máximo 3 caracteres.");
        }
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
               ", idCurso='" + idCurso + '\'' +
               ", contenido='" + contenido + '\'' +
               ", alternativas=" + alternativas +
               ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
               '}';
    }
}
