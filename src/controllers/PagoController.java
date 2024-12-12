package controllers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PagoController {

    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtMontoIngresado;
    @FXML
    private DatePicker dpFechaPago;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label lblCodigoPago;
    @FXML
    private Button btnDepositar;
    @FXML
    private GridPane rootPane;
 
    public void initialize() {
        // Inicializa la imagen de fondo
        //rootPane.getStylesheets().add(getClass().getResource("/estilos/styleCliente.css").toExternalForm());
        // Obtener la referencia al Stage (ventana)


        // Crear y asignar ToggleGroup a los RadioButtons
    }

    @FXML
    private void depositarAction(ActionEvent event) {
        String dni = txtDNI.getText();
        String monto = txtMontoIngresado.getText();
        LocalDate fecha = dpFechaPago.getValue();
        String descripcion = txtDescripcion.getText();
        System.out.println(dni);
        System.out.println(monto);
        System.out.println(fecha);
        System.out.println(descripcion);

        if (estanLLenos(dni, monto, fecha, descripcion)) {
            Optional<ButtonType> result = mostrarMensajeAlerta(Alert.AlertType.INFORMATION, "Confirmación", "¿Seguro que desea depositar?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Depósito exitoso");
                lblCodigoPago.setText(generarCodigoPago());
                limpiarCampos();
            }
        } else {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Por favor, complete todos los campos.");
        }
    }

    private boolean estanLLenos(String dni, String monto, LocalDate fecha, String descripcion) {
        return !dni.isEmpty() && !monto.isEmpty() && !fecha.equals("") && !descripcion.isEmpty();
    }

    private String generarCodigoPago() {
        Random rand = new Random();
        int randomNum = rand.nextInt(900000) + 100000; // Genera un número aleatorio de 6 dígitos
        return String.valueOf(randomNum);
    }

    private Optional<ButtonType> mostrarMensajeAlerta(Alert.AlertType tipo, String titulo, String message) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    private void limpiarCampos() {
        txtDNI.setText("");
        txtMontoIngresado.setText("");
        txtDescripcion.setText("");
        dpFechaPago.setValue(null);
    }

    @FXML
    private void salirAction(ActionEvent event) {
        //logica para salir de l ventan
        Optional<ButtonType> result = mostrarMensajeAlerta(Alert.AlertType.INFORMATION, "Confirmacion", "Seguro que desea salir?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Saliendo..."); // Actualizar la tabla después de eliminar
            //logica para salir
            limpiarCampos();

            // Cerrar la ventana actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

}
