package models;
public class HorarioDisponible {
    private String idioma;
    private String horario; // Nuevo campo que combina Hora Inicio y Fin
    private String dia;
    private String fechaInicio;
    private String fechaFin;
    private String docente;
    private int vacantes;

    // Constructor
    public HorarioDisponible(String idioma, String horario, String dia, String fechaInicio, 
                             String fechaFin, String docente, int vacantes) {
        this.idioma = idioma;
        this.horario = horario;
        this.dia = dia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.docente = docente;
        this.vacantes = vacantes;
    }

    // Getters y Setters
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getDocente() { return docente; }
    public void setDocente(String docente) { this.docente = docente; }

    public int getVacantes() { return vacantes; }
    public void setVacantes(int vacantes) { this.vacantes = vacantes; }
}
