package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PagoController {
    @FXML
    private TextField txtMonto;
    @FXML
    private Label lblRespuesta;

    @FXML
    private void realizarPago() {
        String monto = txtMonto.getText();
        // Implementa la lógica de pago aquí
        lblRespuesta.setText("Pago de " + monto + " realizado con éxito.");
    }
}
