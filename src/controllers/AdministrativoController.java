package controllers;

import dao.HorarioDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.CursoActivo;
import models.HorarioDisponible;
import models.InformeCurso;
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
    @FXML
    private Tab columAdm2;
    @FXML
    private Tab columAdm3;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<CursoActivo> tableviewCursosActivos;
    @FXML
    private TableColumn<CursoActivo, String> colIdioma;
    @FXML
    private TableColumn<CursoActivo, String> colHorario;
    @FXML
    private TableColumn<CursoActivo, String> colDia;
    @FXML
    private TableColumn<CursoActivo, String> colFechaInicio;
    @FXML
    private TableColumn<CursoActivo, String> colFechaFin;
    @FXML
    private TableColumn<CursoActivo, String> colDocente2;
    @FXML
    private TableColumn<CursoActivo, Integer> colVacantes;
    @FXML
    private TableColumn<CursoActivo, Integer> colIdHorario;
    @FXML
    private TextField txtmodulo;
    @FXML
    private TextField txtFechaGeneracion;
    @FXML
    private TextField txtPuntajePromedio;
    @FXML
    private TextField txtTotalEstudiantesInscritos;
    @FXML
    private TextField txtPorcentajeAprobados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuración de la pestaña Presentacion1
        colDocente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecializacion.setCellValueFactory(new PropertyValueFactory<>("especializacion"));

        ObservableList<Usuario> listaDocentes = UsuarioDAO.obtenerDocentes();
        tbldocente.setItems(listaDocentes);

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

        // Configuración de la pestaña Presentacion2
        colIdHorario.setCellValueFactory(new PropertyValueFactory<>("idHorario"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colDocente2.setCellValueFactory(new PropertyValueFactory<>("docente"));
        colVacantes.setCellValueFactory(new PropertyValueFactory<>("vacantes"));

        cargarCursosActivos(); // Llenar la tabla de cursos activos
    }
   

    @FXML
    private void btnSalir(ActionEvent event) throws IOException {
        // Mostrar confirmación antes de salir
        Optional<ButtonType> result = mostrarMensajeAlerta(Alert.AlertType.CONFIRMATION, "Confirmación", "¿Seguro que desea salir?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Saliendo...");

            // Cerrar la sesión del usuario actual
            SesionUsuario.getInstancia().cerrarSesion();

            // Cargar el archivo FXML de la vista Login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            Parent root = loader.load();

            // Aplicar los estilos a la nueva escena
            Scene loginScene = new Scene(root);
            loginScene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());

            // Obtener el escenario actual y cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.centerOnScreen();
            stage.show();
        }
    }
    
    private Optional<ButtonType> mostrarMensajeAlerta(Alert.AlertType tipo, String titulo, String message) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
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
    
    private void cargarCursosActivos() {
        ObservableList<CursoActivo> listaCursos = UsuarioDAO.obtenerCursosActivos();
        tableviewCursosActivos.setItems(listaCursos);
    }
    

    @FXML
    private void btnSiguiente(ActionEvent event) {
        tabPane.getSelectionModel().select(columAdm2);
    }
    
    private HorarioDAO horarioDAO = new HorarioDAO();

    @FXML
    private void btnGenerarInforme(ActionEvent event) {
        try {
            CursoActivo cursoSeleccionado = tableviewCursosActivos.getSelectionModel().getSelectedItem();
            if (cursoSeleccionado != null) {
                int idHorarioSeleccionado = cursoSeleccionado.getIdHorario();

                // Llamar al método del DAO
                InformeCurso informe = horarioDAO.generarInformeCurso(idHorarioSeleccionado);

                // Actualizar los TextField con los valores obtenidos
                if (informe != null) {
                    txtmodulo.setText(String.valueOf(informe.getIdModulo()));
                    txtFechaGeneracion.setText(informe.getFechaGeneracionInforme());
                    txtPuntajePromedio.setText(String.format("%.2f", informe.getPuntajePromedio()));
                    txtTotalEstudiantesInscritos.setText(String.valueOf(informe.getTotalEstudiantes()));
                    txtPorcentajeAprobados.setText(String.format("%.2f", informe.getPorcentajeAprobados()));

                    // Cambiar a la pestaña columAdm3
                    tabPane.getSelectionModel().select(columAdm3);
                } else {
                    showAlert("No se pudo generar el informe", "Revise los datos seleccionados.");
                }
            } else {
                showAlert("Error", "Debe seleccionar un horario válido.");
            }
        } catch (Exception e) {
            showAlert("Error", "Ocurrió un problema al generar el informe.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        // Seleccionar el tab Presentación 1
        tabPane.getSelectionModel().select(columAdm1);
    }    


}