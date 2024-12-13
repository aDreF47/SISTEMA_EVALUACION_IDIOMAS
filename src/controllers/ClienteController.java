package controllers;

import java.io.IOException;
import java.util.Optional;

import dao.HorarioDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import models.HorarioDisponible;
import models.Usuario;

/**
 *
 * @author nando
 */
public class ClienteController {

    @FXML
    private TableView<?> tablaReporte;
    @FXML
    private TableColumn<?, ?> horasRepDocente;
    
    @FXML
    private TextField txtCodigoPago;
    @FXML
    private TableColumn<?, ?> colIdiomaMat;
    
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
    public void initialize() {
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
    
    public void cargarDatosUsuario() {
        Usuario usuario = SesionUsuario.getInstancia().getUsuarioActual();

        if (usuario != null) {
            lblNombre.setText(usuario.getNombre());
            lblApellido.setText(usuario.getApellido());
            lblCodigoEstudiante.setText(String.valueOf(usuario.getIdUsuario()));
            lblEmail.setText(usuario.getEmail());
        }
    }

    public void cargarDatosUsuario(Usuario usuario) {
        lblNombre.setText(usuario.getNombre());
        lblApellido.setText(usuario.getApellido());
        lblCodigoEstudiante.setText(String.valueOf(usuario.getIdUsuario()));
        lblEmail.setText(usuario.getEmail());
    }
    
    

    @FXML
    private void btnSalirAction(ActionEvent event) throws IOException {
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
    private void AdquirirAction(ActionEvent event) {
        paneCliente.lookup(".tab-header-area").setVisible(false); // Oculta el área del encabezado
        paneCliente.getSelectionModel().select(tabMatricula);
    }

    @FXML
    private void btnAceptaAction(ActionEvent event) {
    }

    @FXML
    private void btnAquirirCurso(ActionEvent event) {
        paneCliente.getSelectionModel().select(tabMisCursos);
    }

    @FXML
    private void btnRendirExamen(ActionEvent event) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void TransaccionAction(ActionEvent event) {
        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PagoView.fxml"));
            Parent root = fxmlLoader.load();

            // Crear un nuevo escenario (Stage) para la ventana
            Stage stage = new Stage();
            stage.setTitle("Pago");
            stage.setScene(new Scene(root));

            //Configurar como modal para bloquear interacción con la ventana principal
            stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal mientras la nueva está abierta
            stage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Define quién es la ventana padre

            // Mostrar la nueva ventana
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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

    @FXML
    private void btnRegresarPanelHorarioAction(ActionEvent event) {
        paneCliente.getSelectionModel().select(tabModulos);

    }
 
}
