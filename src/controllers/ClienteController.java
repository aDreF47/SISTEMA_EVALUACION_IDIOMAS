/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Label idnombre;
    @FXML
    private Label docentecodigo;
    @FXML
    private Label docentecorreo;
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
    private void HorarioAction(ActionEvent event) {
    }

    @FXML
    private void ReporteAction(ActionEvent event) {
    }

    @FXML
    private void btnSalirAction(ActionEvent event) throws IOException {
        Parent regresarLogin = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
        Scene registrarScene = new Scene(regresarLogin);
        
        registrarScene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());
        // Obtener el escenario actual y cambiar la escena
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(registrarScene);
        window.show();
    
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
            // Cargar la nueva vista desde examenView.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/examenView.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde el evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
    private void DisponibilidadAction(ActionEvent event) {
    }
 
    
}
