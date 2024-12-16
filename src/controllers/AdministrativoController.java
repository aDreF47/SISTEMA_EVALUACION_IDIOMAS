package controllers;

import dao.UsuarioDAO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.HorarioDisponible;
import models.Usuario;

public class AdministrativoController implements Initializable {

    @FXML
    private Tab columAdm1;
    @FXML
    private TextField txtDiaClases;
    @FXML
    private ChoiceBox<String> choiceIdioma, choiceModulo, choiceHorarioInicio, choiceHorarioFin;
    @FXML
    private Spinner<Integer> spinnerNivel;
    @FXML
    private Button btnSalir;
    @FXML
    private TableColumn<Usuario, String> colDocente;
    @FXML
    private TableView<Usuario> tbldocente;
    @FXML
    private TableColumn<Usuario, String> colEspecializacion;
    @FXML
    private TextField txtVacantes;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar las columnas
        colDocente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecializacion.setCellValueFactory(new PropertyValueFactory<>("especializacion"));

        // Obtener la lista de docentes y cargarla en la tabla
        ObservableList<Usuario> listaDocentes = UsuarioDAO.obtenerDocentes();
        tbldocente.setItems(listaDocentes);

        // Mostrar en consola para verificar
        listaDocentes.forEach(docente -> System.out.println(
                "Docente: " + docente.getNombre() + " " + docente.getApellido() +
                ", Especialización: " + docente.getEspecializacion()
        ));
        
        

        // Llenar ChoiceBox de Idiomas
        choiceIdioma.getItems().addAll("Inglés", "Portugués");

        // Llenar ChoiceBox de Módulos
        choiceModulo.getItems().addAll("Básico", "Intermedio", "Avanzado");

        // Llenar Spinner de Nivel (1 al 12)
        spinnerNivel.setValueFactory(new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));

        // Generar horarios de 8:00 a.m. a 8:00 p.m.
        IntStream.range(8, 21).forEach(hour -> {
            String time = String.format("%02d:00 a.m.", hour > 12 ? hour - 12 : hour);
            if (hour >= 12) time = String.format("%02d:00 p.m.", hour - 12);
            choiceHorarioInicio.getItems().add(time);
            choiceHorarioFin.getItems().add(time);
        });
        
        
        
    }    

    @FXML
    private void btnSalir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close(); // Cierra la ventana actual
    }

    @FXML
    private void btnAsignarDocente(ActionEvent event) {
        // Implementar funcionalidad si se requiere más adelante
    }

    @FXML
    private void btnCrearHorario(ActionEvent event) {
        // Capturar datos para MODULO
        String idiomaSeleccionado = choiceIdioma.getValue();
        String idIdioma = idiomaSeleccionado.equals("Inglés") ? "001" : "002";

        String moduloSeleccionado = choiceModulo.getValue();
        int nivel = spinnerNivel.getValue();
        String nombreModulo = idiomaSeleccionado + " " + moduloSeleccionado;

        String vacantesTexto = txtVacantes.getText();
        int vacantes = 0;

        try {
            vacantes = Integer.parseInt(vacantesTexto);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para vacantes.");
            return;
        }

        // Insertar en la tabla MODULO
        boolean resultadoModulo = UsuarioDAO.crearModulo(idIdioma, nombreModulo, nivel, vacantes);
        if (!resultadoModulo) {
            System.out.println("Error al crear el módulo.");
            return;
        }

        // Obtener el último idModulo generado
        int idModulo = UsuarioDAO.obtenerUltimoIdModulo();
        if (idModulo == 0) {
            System.out.println("Error: No se pudo obtener el ID del módulo.");
            return;
        }

        // Capturar datos para HORARIO
        String horaInicio = convertirHora(choiceHorarioInicio.getValue());
        String horaFin = convertirHora(choiceHorarioFin.getValue());
        String diaSemana = txtDiaClases.getText().trim();
        String fechaInicio = dpFechaInicio.getValue().toString();
        String fechaFin = dpFechaFin.getValue().toString();

        // Insertar en la tabla HORARIO
        boolean resultadoHorario = UsuarioDAO.crearHorario(idModulo, horaInicio, horaFin, diaSemana, fechaInicio, fechaFin);
        if (!resultadoHorario) {
            System.out.println("Error al crear el horario.");
            return;
        }

        // Obtener el último idHorario generado
        int idHorario = UsuarioDAO.obtenerUltimoIdHorario();
        if (idHorario == 0) {
            System.out.println("Error: No se pudo obtener el ID del horario.");
            return;
        }

        // EXTRAER EL ID DEL DOCENTE SELECCIONADO EN LA TABLEVIEW
        Usuario docenteSeleccionado = tbldocente.getSelectionModel().getSelectedItem();
        if (docenteSeleccionado == null) {
            System.out.println("Error: Seleccione un docente de la tabla.");
            return;
        }

        int idUsuario = docenteSeleccionado.getIdUsuario(); // Obtener ID del docente

        // Insertar en la tabla ASIGNACION DOCENTES
        boolean resultadoAsignacion = UsuarioDAO.crearAsignacionDocente(idHorario, idUsuario);
        if (resultadoAsignacion) {
            System.out.println("Asignación del docente realizada correctamente.");
        } else {
            System.out.println("Error al asignar el docente.");
        }
    }


    
    
    // Método para obtener las horas y convertirlas a formato HH:MI:SS
    private String convertirHora(String hora) {
        if (hora == null) return null;

        String[] partes = hora.split(" ");
        String tiempo = partes[0]; // Ejemplo: "08:00"
        String meridiano = partes[1]; // Ejemplo: "a.m." o "p.m."

        // Convertir a formato 24 horas
        String[] horaMinuto = tiempo.split(":");
        int horaInt = Integer.parseInt(horaMinuto[0]);
        if (meridiano.equalsIgnoreCase("p.m.") && horaInt != 12) {
            horaInt += 12;
        } else if (meridiano.equalsIgnoreCase("a.m.") && horaInt == 12) {
            horaInt = 0;
        }

        return String.format("%02d:%02d:00", horaInt, Integer.parseInt(horaMinuto[1]));
    }



}