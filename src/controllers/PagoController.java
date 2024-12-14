package controllers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import dao.PagoDAO;
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
import models.Pago;
import models.Usuario;

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

    }

    public void cargarDatosUsuario(Usuario usuario) {
        if (usuario != null) {
            txtDNI.setText(usuario.getDni()); // Autocompleta el DNI del usuario logueado
            txtMontoIngresado.setText("20.00"); // Fija el monto en 20 soles
            txtMontoIngresado.setDisable(true); // Desactiva el campo de monto para evitar edición
        }
    }

    @FXML
private void depositarAction(ActionEvent event) {
    try {
        // Obtener datos desde la interfaz gráfica
        String dni = txtDNI.getText();
        String descripcion = txtDescripcion.getText();
        LocalDate fecha = dpFechaPago.getValue();
        double monto = 20.00; // Monto fijo
        String codPago = generarCodigoPago();

        if (dni == null || dni.isEmpty() || descripcion == null || descripcion.isEmpty() || fecha == null) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Por favor, complete todos los campos.");
            return;
        }

        // Crear el objeto Pago
        Pago nuevoPago = new Pago(
            SesionUsuario.getInstancia().getUsuarioActual().getIdUsuario(), // ID del usuario logueado
            null, // idMatricula es NULL porque aún no está matriculado
            monto,
            java.sql.Date.valueOf(fecha),
            descripcion,
            codPago,
            0 // Estado: sin usar
        );

        // Insertar el pago en la base de datos
        PagoDAO pagoDAO = new PagoDAO();
        boolean exito = pagoDAO.insertarPago(nuevoPago);

        if (exito) {
            lblCodigoPago.setText(codPago); // Mostrar el código generado
            mostrarMensajeAlerta(Alert.AlertType.INFORMATION, "Éxito", "Depósito registrado con éxito.");
            limpiarCampos();
        } else {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el depósito. Intente de nuevo.");
        }
    } catch (Exception e) {
        mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error inesperado.");
        e.printStackTrace();
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
