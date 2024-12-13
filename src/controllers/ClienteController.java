package controllers;

import java.io.IOException;
import java.util.Optional;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private TableColumn<?, ?> colIdioma;
    @FXML
    private TableColumn<?, ?> colHoraI;
    @FXML
    private TableView<?> tableCursos;
    @FXML
    private TableColumn<?, ?> colHoraFin;
    @FXML
    private TableColumn<?, ?> colDia;
    @FXML
    private TableColumn<?, ?> colDocente;
    @FXML
    private TableColumn<?, ?> colVacante;
    @FXML
    private TextField txtCodigoPago;
    @FXML
    private TableColumn<?, ?> colIdiomaMat;
    @FXML
    private TableColumn<?, ?> colHoraInicio;
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
    private TableColumn<?, ?> colFechaIni;
    @FXML
    private TableColumn<?, ?> colFechFin;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCodigoEstudiante;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblApellido;
    

    @FXML
    private void btnSalirAction(ActionEvent event) throws IOException {
        Optional<ButtonType> result = mostrarMensajeAlerta(Alert.AlertType.INFORMATION, "Confirmacion", "Seguro que desea salir?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Saliendo..."); // Actualizar la tabla después de eliminar
            //logica para salir
            //limpiarCampos();

            Parent regresarLogin = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
            Scene registrarScene = new Scene(regresarLogin);
            
            registrarScene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());
            // Obtener el escenario actual y cambiar la escena
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            
            window.setScene(registrarScene);
            window.centerOnScreen();
            window.show();
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
