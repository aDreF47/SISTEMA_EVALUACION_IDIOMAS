package models;

import java.util.List;

public class BancoPregunta {
    
    //
    private String modulo; 
    private String contenido; 
    private String alternativa1;
    private String alternativa2;
    private String alternativa3;
    private String alternativa4;
    private String respuestaCorrecta; 

    public BancoPregunta() {
    }

    public BancoPregunta(String modulo, String contenido, String alternativa1, String alternativa2, String alternativa3, String alternativa4, String respuestaCorrecta) {
        this.modulo = modulo;
        this.contenido = contenido;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.alternativa3 = alternativa3;
        this.alternativa4 = alternativa4;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    

   /* @Override
    public String toString() {
        return "Pregunta{" +
               "idPregunta=" + idPregunta +
               ", modulo='" + modulo + '\'' +
               ", contenido='" + contenido + '\'' +
               ", alternativas=" + alternativas +
               ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
               '}';
    }*/

    /**
     * @return the modulo
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the alternativa1
     */
    public String getAlternativa1() {
        return alternativa1;
    }

    /**
     * @param alternativa1 the alternativa1 to set
     */
    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    /**
     * @return the alternativa2
     */
    public String getAlternativa2() {
        return alternativa2;
    }

    /**
     * @param alternativa2 the alternativa2 to set
     */
    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    /**
     * @return the alternativa3
     */
    public String getAlternativa3() {
        return alternativa3;
    }

    /**
     * @param alternativa3 the alternativa3 to set
     */
    public void setAlternativa3(String alternativa3) {
        this.alternativa3 = alternativa3;
    }

    /**
     * @return the alternativa4
     */
    public String getAlternativa4() {
        return alternativa4;
    }

    /**
     * @param alternativa4 the alternativa4 to set
     */
    public void setAlternativa4(String alternativa4) {
        this.alternativa4 = alternativa4;
    }

    /**
     * @return the respuestaCorrecta
     */
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    /**
     * @param respuestaCorrecta the respuestaCorrecta to set
     */
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
}
