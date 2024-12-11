/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private TabPane PaneDocente;
    @FXML
    private Tab tabDisponibilidad;
    @FXML
    private TableView<?> tableCursoDisponible;
    @FXML
    private TableColumn<?, ?> colCursoDisponible;
    @FXML
    private TableColumn<?, ?> colHoraDisponible;
    @FXML
    private TableView<?> tableCursoElegido;
    @FXML
    private TableColumn<?, ?> colCursoElegido;
    @FXML
    private TableColumn<?, ?> colHoraElegido;
    @FXML
    private TextField txtDiaDisponible;
    @FXML
    private TextField txtHoraInicio;
    @FXML
    private TextField txtHoraFinal;
    @FXML
    private Tab tabHorarioGenerado;
    @FXML
    private VBox horarioGeneradoContainer;
    @FXML
    private Text mensajeNoMasPropuestas;
    @FXML
    private Tab tabReporte;
    @FXML
    private TableView<?> tablaReporte;
    @FXML
    private TableColumn<?, ?> cicloRepDocente;
    @FXML
    private TableColumn<?, ?> seccionRepDocente;
    @FXML
    private TableColumn<?, ?> cursoRepDocente;
    @FXML
    private TableColumn<?, ?> horasRepDocente;
    @FXML
    private TableColumn<?, ?> deRepDocente;
    @FXML
    private TableColumn<?, ?> hastaRepDocente;
    @FXML
    private TableColumn<?, ?> diaRepDocente;
    @FXML
    private Button btnDescargarReporte;

    @FXML
    private void DisponibilidadAction(ActionEvent event) {
    }

    @FXML
    private void HorarioAction(ActionEvent event) {
    }

    @FXML
    private void ReporteAction(ActionEvent event) {
    }

    @FXML
    private void SalirAction(ActionEvent event) {
    }

    @FXML
    private void ElegirCursoAction(ActionEvent event) {
    }

    @FXML
    private void EditarCursoAction(ActionEvent event) {
    }

    @FXML
    private void BorrarCursoAction(ActionEvent event) {
    }

    @FXML
    private void AgregarDisponibilidadAction(ActionEvent event) {
    }

    @FXML
    private void GenerarHorarioAction(ActionEvent event) {
    }

    @FXML
    private void GenerarNuevoHorarioAction(ActionEvent event) {
    }

    @FXML
    private void SeleccionarHorarioAction(ActionEvent event) {
    }

    @FXML
    private void generarReporte(ActionEvent event) {
    }
    
}
