
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ExamenController {

    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtapellido;
    @FXML
    private TextField txydni;
    @FXML
    private RadioButton rbp1a;
    @FXML
    private ToggleGroup grupo1;
    @FXML
    private RadioButton rbp1b;
    @FXML
    private RadioButton rbp1c;
    @FXML
    private RadioButton rbp1d;
    @FXML
    private RadioButton rbp2a;
    @FXML
    private ToggleGroup grupo2;
    @FXML
    private RadioButton rbp2b;
    @FXML
    private RadioButton rbp2c;
    @FXML
    private RadioButton rbp2d;
    @FXML
    private RadioButton rbp3a;
    @FXML
    private ToggleGroup grupo3;
    @FXML
    private RadioButton rbp3b;
    @FXML
    private RadioButton rbp3c;
    @FXML
    private RadioButton rbp3d;
    @FXML
    private RadioButton rbp4a;
    @FXML
    private ToggleGroup grupo4;
    @FXML
    private RadioButton rbp4b;
    @FXML
    private RadioButton rbp4c;
    @FXML
    private RadioButton rbp4d;
    @FXML
    private RadioButton rbp5a;
    @FXML
    private ToggleGroup grupo5;
    @FXML
    private RadioButton rbp5b;
    @FXML
    private RadioButton rbp5c;
    @FXML
    private RadioButton rbp5d;
    @FXML
    private RadioButton rbp6a;
    @FXML
    private ToggleGroup grupo6;
    @FXML
    private RadioButton rbp6b;
    @FXML
    private RadioButton rbp6c;
    @FXML
    private RadioButton rbp6d;
    @FXML
    private RadioButton rbp7a;
    @FXML
    private ToggleGroup grupo7;
    @FXML
    private RadioButton rbp7b;
    @FXML
    private RadioButton rbp7c;
    @FXML
    private RadioButton rbp7d;
    @FXML
    private RadioButton rbp8a;
    @FXML
    private ToggleGroup grupo8;
    @FXML
    private RadioButton rbp8b;
    @FXML
    private RadioButton rbp8c;
    @FXML
    private RadioButton rbp8d;
    @FXML
    private RadioButton rbp9a;
    @FXML
    private ToggleGroup grupo9;
    @FXML
    private RadioButton rbp9b;
    @FXML
    private RadioButton rbp9c;
    @FXML
    private RadioButton rbp9d;
    @FXML
    private RadioButton rbp10a;
    @FXML
    private ToggleGroup grupo10;
    @FXML
    private RadioButton rbp10b;
    @FXML
    private RadioButton rbp10c;
    @FXML
    private RadioButton rbp10d;
    @FXML
    private Label nombreCurso;
    @FXML
    private Label nombreDocente;
    @FXML
    private Label nivelExamen;
    @FXML
    private Label pregunta1;
    @FXML
    private Label pregunta2;
    @FXML
    private Label pregunta3;
    @FXML
    private Label pregunta4;
    @FXML
    private Label pregunta5;
    @FXML
    private Label pregunta6;
    @FXML
    private Label pregunta7;
    @FXML
    private Label pregunta8;
    @FXML
    private Label pregunta9;
    @FXML
    private Label pregunta10;
    @FXML
    private TabPane tabPane;

    @FXML
    private void btnSiguiente1(ActionEvent event) {
        tabPane.getSelectionModel().selectNext();
    }

    @FXML
    private void btnSiguiente2(ActionEvent event) {
        tabPane.getSelectionModel().selectNext();
    }

    @FXML
    private void btnSiguiente3(ActionEvent event) {
        tabPane.getSelectionModel().selectNext();
    }

    @FXML
    private void btnSiguiente4(ActionEvent event) {
        tabPane.getSelectionModel().selectNext();
    }

    @FXML
    private void btnEntregarExamen(ActionEvent event) {
    }

    @FXML
    private void btnVerCalificacion(ActionEvent event) {
        // Crear una alerta de tipo información
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calificación");
        alert.setHeaderText("Resultados del Examen");
        alert.setContentText("Tu examen ha sido calificado.");

        // Añadir un botón personalizado "Ver Respuestas"
        ButtonType verRespuestasButton = new ButtonType("Ver Respuestas");
        ButtonType cerrarButton = new ButtonType("Cerrar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(verRespuestasButton, cerrarButton);

        // Manejar la acción del botón
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == verRespuestasButton) {
            try {
                // Cargar la vista de RespuestasView.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RespuestasView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Obtener el Stage actual
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnRetrocer1(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    @FXML
    private void btnRetrocer2(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    @FXML
    private void btnRetrocer3(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    @FXML
    private void btnRetrocer4(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    void configureCloseHandler(Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
