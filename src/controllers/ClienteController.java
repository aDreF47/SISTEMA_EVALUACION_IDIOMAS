package controllers;

import dao.EstudianteDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import dao.HorarioDAO;
import dao.PagoDAO;
import dao.UsuarioDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Estudiante;
import models.HorarioDisponible;
import models.Pago;
import models.Usuario;

public class ClienteController {

    @FXML
    private TableColumn<?, ?> horasRepDocente;

    @FXML
    private TextField txtCodigoPago;

    @FXML
    private TableColumn<?, ?> colHoraF;
    @FXML
    private TableColumn<?, ?> colDiaCurso;
    @FXML
    private TableColumn<?, ?> colDocen;
    @FXML
    private Tab tabModulos;
    @FXML
    private Tab tabMatricula;
    @FXML
    private Tab tabMisCursos;
    @FXML
    private TabPane paneCliente;
    @FXML
    private DatePicker datePickerPago;
    @FXML
    private Button btnAceptarPago;
    @FXML
    private Label lblIdioma;
    @FXML
    private Label lblHoraInicio;
    @FXML
    private Label lblHoraFin;
    @FXML
    private Label lblDia;
    @FXML
    private Label lblDocente;
    @FXML
    private Label lblVacantes;
    @FXML
    private Button btnAdquirirCurso;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido;
    @FXML
    private Label lblCodigoEstudiante;
    @FXML
    private Label lblEmail;

    @FXML
    private TableView<HorarioDisponible> tablaHorarios;
    @FXML
    private TableColumn<HorarioDisponible, String> colIdioma;
    @FXML
    private TableColumn<HorarioDisponible, String> colHorario;
    @FXML
    private TableColumn<HorarioDisponible, String> colDia;
    @FXML
    private TableColumn<HorarioDisponible, String> colFechaInicio;
    @FXML
    private TableColumn<HorarioDisponible, String> colFechaFin;
    @FXML
    private TableColumn<HorarioDisponible, String> colDocente;
    @FXML
    private TableColumn<HorarioDisponible, Integer> colVacantes;

    HorarioDAO horarioDAO = new HorarioDAO();
    @FXML
    private TableColumn<?, ?> colHoraInicioMis;
    @FXML
    private TableView<?> tabladeMisCursos;
    @FXML
    private TableColumn<?, ?> colIdiomaMiscursos;
    @FXML
    private TableColumn<?, ?> colFechaIni;
    @FXML
    private TableColumn<?, ?> colFechaFinMisCursos;
    @FXML
    private TableColumn<?, ?> colCalificacion;

    public void initialize() {
        Platform.runLater(() -> {
            Node tabHeader = paneCliente.lookup(".tab-header-area");
            if (tabHeader != null) {
                tabHeader.setVisible(false);
            } else {
                System.out.println("No se encontró el nodo '.tab-header-area'.");
            }
        });
        
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colDocente.setCellValueFactory(new PropertyValueFactory<>("docente"));
        colVacantes.setCellValueFactory(new PropertyValueFactory<>("vacantes"));

        ObservableList<HorarioDisponible> lista = horarioDAO.obtenerHorariosDisponibles();
        System.out.println("Registros obtenidos: " + lista.size());
        lista.forEach(horario -> System.out.println(horario.getIdioma() + " - " + horario.getDocente()));
        tablaHorarios.setItems(lista);

    }

    private boolean selecionarHorario() {
        boolean respuesta = false;
        // Listener para capturar selección de la tabla de horarios
        tablaHorarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDetallesCurso(newSelection); // Método para cargar detalles en TabPane
            }
        });
        return respuesta;
    }

    private void cargarDetallesCurso(HorarioDisponible horario) {
        lblIdioma.setText("Idioma: " + horario.getIdioma());
        lblHoraInicio.setText("Hora Inicio: " + horario.getHorario().split("-")[0].trim());
        lblHoraFin.setText("Hora Fin: " + horario.getHorario().split("-")[1].trim());
        lblDia.setText("Día: " + horario.getDia());
        lblDocente.setText("Docente: " + horario.getDocente());
        lblVacantes.setText("Vacantes Disponibles: " + horario.getVacantes());
    }

    public void cargarDatosUsuario() {
        Usuario usuario = SesionUsuario.getInstancia().getUsuarioActual();

        if (usuario != null) {
            lblNombre.setText(usuario.getNombre());
            lblApellido.setText(usuario.getApellido());
            lblCodigoEstudiante.setText(String.valueOf(usuario.getIdUsuario()));
            lblEmail.setText(usuario.getEmail());
        }
    }
    
    @FXML
    private void irPanelHorarioAction(ActionEvent event) {
        paneCliente.getSelectionModel().select(tabModulos);
    }

    @FXML
    private void irPanelMisCursosAction(ActionEvent event) {
        paneCliente.getSelectionModel().select(tabMisCursos);
    }

    public void cargarDatosUsuario(Usuario usuario) {
//        lblNombre.setText(usuario.getNombre());
//        lblApellido.setText(usuario.getApellido());
//        lblCodigoEstudiante.setText(String.valueOf(usuario.getIdUsuario()));
//        lblEmail.setText(usuario.getEmail());
    }

    @FXML
    private void btnSalirAction(ActionEvent event) throws IOException {
        // Mostrar confirmación antes de salir
        Optional<ButtonType> result = mostrarMensajeAlerta(Alert.AlertType.CONFIRMATION, "Confirmación",
                "¿Seguro que desea salir?");
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

    /*
    ==================================================
    panel 1 de horarios de los modulos
    ===============================================
    */
    
    @FXML
    private void btnMatriculaAction(ActionEvent event) {
        selecionarHorario();
        paneCliente.getSelectionModel().selectNext(); 
    }
    
    @FXML
    private void TransaccionAction(ActionEvent event) {
        try {
            // Obtener el usuario actual desde la sesión
            Usuario usuarioActual = SesionUsuario.getInstancia().getUsuarioActual();

            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PagoView.fxml"));
            Parent root = fxmlLoader.load();

            // Obtener el controlador de la nueva vista
            PagoController pagoController = fxmlLoader.getController();
            pagoController.cargarDatosUsuario(usuarioActual); // Pasar datos del usuario al controlador

            // Crear un nuevo escenario (Stage) para la ventana
            Stage stage = new Stage();
            stage.setTitle("Pago");
            stage.setScene(new Scene(root));

            // Configurar como modal para bloquear interacción con la ventana principal
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());

            // Mostrar la nueva ventana
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /*
    ==================================================
    panel 2 de matricula
    ===============================================
    */
    
    @FXML
    private void btnAceptaMatriculaAction(ActionEvent event) {
        PagoDAO pagDAO = new PagoDAO();

        String codPago = txtCodigoPago.getText().trim(); // Eliminar espacios
        LocalDate fechaPago = datePickerPago.getValue();
        System.out.println(fechaPago);

        if (fechaPago == null || codPago.isEmpty()) {
            mostrarMensajeAlerta(AlertType.WARNING, "Error", "Debe ingresar código de pago y fecha.");
            return;
        }

        // Convertir LocalDate a String en formato compatible con la base de datos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaPago.format(formatter);

        System.out.println("Código de pago enviado: " + codPago);
        System.out.println("Fecha formateada enviada: " + fechaFormateada);

        // Validar registro del pago
        boolean existeRegistroPago = pagDAO.verificarRegistroPago(codPago, fechaFormateada);
        
        if (!existeRegistroPago) {
            System.out.println("Pago no registrado.");
            return;
        }

        // Validar estado del pago
        boolean estadoPago = pagDAO.verificarEstadoPago(codPago, fechaFormateada);
        if (!estadoPago) {
            mostrarMensajeAlerta(AlertType.WARNING, "Error", "El pago ya ha sido usado.");
            return;
        }

        // Actualizar estado del pago
        boolean estadoActualizado = pagDAO.actualizarEstadoPago(codPago);
        if (estadoActualizado) {
            RegistrarEstudiante(); /// aqui entra y no registrea
            //RegistrarMatricula();
            
            /*lo de aca si muestra*/ mostrarMensajeAlerta(AlertType.INFORMATION, "Éxito", "Matricula exitosa ☻♥");
        } else {
            mostrarMensajeAlerta(AlertType.ERROR, "Error", "No se pudo actualizar el estado del pago.");
        }
    }
    
    private String generarCodigoEstudiante() {
        Random rand = new Random();
        int randomNum = rand.nextInt(90000000) + 10000000; // Genera un número aleatorio de 6 dígitos
        return String.valueOf(randomNum);
    }

    private void RegistrarEstudiante() {
        try {
            EstudianteDAO estDAO = new EstudianteDAO();
            int idUsuario = SesionUsuario.getInstancia().getUsuarioActual().getIdUsuario();

            // Verificar si el estudiante ya existe
            String codigoExistente = estDAO.buscarEstudiante(idUsuario);

            String codEstudiante;
            if (codigoExistente != null) {
                // Si ya existe, usar el código existente
                codEstudiante = codigoExistente;
                System.out.println("Estudiante ya registrado con código: ");
            } else {
                // Si no existe, generar un nuevo código
                codEstudiante = generarCodigoEstudiante();

                // Crear el objeto Estudiante
                Estudiante nuevoEstudiante = new Estudiante(
                        idUsuario,
                        codEstudiante,
                        1 // Estado: sin usar
                );

                System.out.println("Nuevo estudiante, código generado: " + nuevoEstudiante.getCodigo());

                // Insertar el estudiante en la base de datos
                boolean exito = estDAO.insertarEstudiante(nuevoEstudiante);

                if (!exito) {
                    mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar al estudiante. Intente de nuevo.");
                    return;
                }
                System.out.println("Estudiante registrado con éxito.");
            }

            mostrarMensajeAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante registrado con éxito. Código: ");

        } catch (Exception e) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error inesperado.");
            e.printStackTrace();
        }
        
    }
    // private void RegistrarEstudiante() {
    //     try {
    //         String codEstudiante = generarCodigoEstudiante();
            
    //         // Crear el objeto Pago
    //         Estudiante nuevoEstudiante = new Estudiante(
    //             SesionUsuario.getInstancia().getUsuarioActual().getIdUsuario(), // ID del usuario logueado
    //             codEstudiante,
    //             1 // Estado: sin usar
    //         );

    //         System.out.println("fuera: "+nuevoEstudiante.getCodigo());

    //         // Insertar el estudiante en la base de datos
    //         EstudianteDAO estDAO = new EstudianteDAO();
    //         //boolean existeEstudiante = estDAO.buscarEstudiante(nuevoEstudiante);
    //         boolean exito = estDAO.insertarEstudiante(nuevoEstudiante);

    //         if (exito) {
    //             System.out.println("Estudiante registrado con éxito.");
    //             //mostrarMensajeAlerta(Alert.AlertType.INFORMATION, "Éxito", "Estudiante registrado con éxito.");
    //         } else {
    //             System.out.println("No se pudo registrar al estudiante. Intente de nuevo.");
    //             //mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar al estudiante. Intente de nuevo.");
    //         }
    //     } catch (Exception e) {
    //         mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error inesperado.");
    //         e.printStackTrace();
    //     }
        
    // }

    @FXML
    private void btnRegresarPanelHorarioAction(ActionEvent event) {
        paneCliente.getSelectionModel().select(tabModulos);

    }
    
    @FXML
    private void btnAquirirCurso(ActionEvent event) {
    }

    @FXML
    private void validarFechaPagoMatriculaAction(ActionEvent event) {
        LocalDate selectedDate = datePickerPago.getValue();
        LocalDate today = LocalDate.now();

        // Validación de fecha: no permitir días posteriores al actual
        if (selectedDate != null && selectedDate.isAfter(today)) {
            mostrarMensajeAlerta(AlertType.ERROR, "Fecha inválida", "No se puede seleccionar una fecha futura.");
            datePickerPago.setValue(today); // Restablece a la fecha actual
        }
    }

    
    
        // @FXML
    // private void btnAquirirCurso(ActionEvent event) {
    // PagoDAO pagDAO = new PagoDAO();
    // String codPago = txtCodigoPago.getText();
    // LocalDate fechaPago = datePickerPago.getValue();

    // boolean existeRegistroPago = pagDAO.verificarRegistroPago(codPago,
    // fechaPago);
    // boolean estadoPago = pagDAO.verificarEstadoPago(codPago, fechaPago);

    // if (!existeRegistroPago) {
    // mostrarMensajeAlerta(AlertType.ERROR, "Error", "Pago no registrado");
    // imgCheck.setVisible(false); // Ocultar el check
    // } else if (!estadoPago) {
    // mostrarMensajeAlerta(AlertType.WARNING, "Error", "El pago ya ha sido usado");
    // imgCheck.setVisible(false); // Ocultar el check
    // } else {
    // mostrarMensajeAlerta(AlertType.INFORMATION, "Éxito", "Pago verificado
    // correctamente");
    // imgCheck.setVisible(true); // Mostrar el check
    // }
    // // paneCliente.getSelectionModel().select(tabMisCursos);
    // }

    
    /*
    ==================================================
    panel 3 de mis cursos
    ===============================================
    */

    @FXML
    private void btnClases(ActionEvent event) {
        System.out.println("Ingresando a clases");
    }
    @FXML
    private void btnRendirExamen(ActionEvent event) {
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Estás seguro de que quieres rendir el examen?");
            // alert.setContentText("Esta acción te llevará al inicio del examen. Asegúrate
            // de estar preparado.");

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Aquí se maneja la lógica si el usuario confirma
                System.out.println("Iniciando el examen...");
                // Puedes agregar aquí el código para iniciar la funcionalidad del examen
                // Cargar la vista ExamenView.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/examenView.fxml"));
                Parent root = loader.load();

                // Crear un nuevo Stage para la ventana de examen
                Stage stage = new Stage();
                stage.setTitle("Examen");
                Scene scene = new Scene(root);

                // Agregar la hoja de estilos
                scene.getStylesheets().add(getClass().getResource("/styles/styleExamen.css").toExternalForm());

                // Configurar el Stage
                stage.setScene(scene);
                stage.centerOnScreen();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(((Node) event.getSource()).getScene().getWindow());

                // Mostrar la ventana sin cerrar ClienteView.fxml
                stage.show();
            } else {
                // Aquí se maneja si el usuario cancela
                System.out.println("El usuario canceló el inicio del examen.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
