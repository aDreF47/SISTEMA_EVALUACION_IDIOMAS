package models;

public class CursoActivo {
    private int idHorario;
    private String idioma;
    private String horario;
    private String dia;
    private String fechaInicio;
    private String fechaFin;
    private String docente;
    private int vacantes;

    // Constructor
    public CursoActivo(int idHorario, String idioma, String horario, String dia,
                       String fechaInicio, String fechaFin, String docente, int vacantes) {
        this.idHorario = idHorario;
        this.idioma = idioma;
        this.horario = horario;
        this.dia = dia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.docente = docente;
        this.vacantes = vacantes;
    }

    // Getters
    public int getIdHorario() { return idHorario; }
    public String getIdioma() { return idioma; }
    public String getHorario() { return horario; }
    public String getDia() { return dia; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public String getDocente() { return docente; }
    public int getVacantes() { return vacantes; }
}