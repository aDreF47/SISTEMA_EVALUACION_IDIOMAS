/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.BancoPreguntaDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Insets;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import models.BancoPregunta;

/**
 *
 * @author nando
 */
public class DocenteController {
    
    

    @FXML
    private AnchorPane panelDocente;
    @FXML
    private Label lblTeacher;
    @FXML
    private Button btnClose;
    @FXML
    private FontAwesomeIconView iconClose;
    @FXML
    private Button btnMinus;
    @FXML
    private FontAwesomeIconView iconMinus;
    @FXML
    private FontAwesomeIconView iconGraduation;
    @FXML
    private AnchorPane panelUser;
    @FXML
    private FontAwesomeIconView iconUser;
    @FXML
    private Label lblWelcome;
    @FXML
    private Line lineaSep;
    @FXML
    private Button btnStudent;
    @FXML
    private FontAwesomeIconView iconStudents;
    @FXML
    private Button btnEval;
    @FXML
    private FontAwesomeIconView iconBook;
    @FXML
    private TextField textfEstSearch;
    @FXML
    private Button btnEstSearch;
    @FXML
    private FontAwesomeIconView iconEstSearch;
    @FXML
    private TableView<?> tableEst;
    @FXML
    private TextField testfEvalSearch;
    @FXML
    private Button btnEvalSearch;
    @FXML
    private FontAwesomeIconView iconEvalSearch;
    @FXML
    private TableView<BancoPregunta> tableEval;
    @FXML
    private Label lblEvalBP;
    @FXML
    private Label lblEvalRE;
    @FXML
    private Label lblEvalFecha;
    @FXML
    private DatePicker EvalCalendary;
    @FXML
    private Label lblEvalTipo;
    @FXML
    private TextField textfEvalTipo;
    @FXML
    private Button btnRegEval;
    @FXML
    private TableColumn<?, ?> colEstC1;
    @FXML
    private TableColumn<?, ?> colEstC2;
    @FXML
    private AnchorPane formEstudiantes;
    @FXML
    private AnchorPane formEvaluaciones;
    @FXML
    private Label lblEvalHora1;
    @FXML
    private TextField textfEvalHora1;
    @FXML
    private TableColumn<BancoPregunta, String> colBPModulo;
    @FXML
    private TableColumn<BancoPregunta, String> colBPPregunta;
    @FXML
    private TableColumn<BancoPregunta, String> colBPop1;
    @FXML
    private TableColumn<BancoPregunta, String> colBPop2;
    @FXML
    private TableColumn<BancoPregunta, String> colBPop3;
    @FXML
    private TableColumn<BancoPregunta, String> colBPop4;
    @FXML
    private TableColumn<BancoPregunta, String> colBPRespuesta;
    
    BancoPreguntaDAO bancoDAO = new BancoPreguntaDAO();
    
    @FXML
    private Button btnExam;
    @FXML
    private FontAwesomeIconView iconExam;
    @FXML
    private TabPane tabPaneExam;
    @FXML
    private Tab tabP1_2;
    @FXML
    private Tab tabP3_4;
    @FXML
    private AnchorPane formExam;
    @FXML
    private TextField txtPregunta;
    @FXML
    private TextField txtOpcion1;
    @FXML
    private Label lblEvalMod1;
    @FXML
    private TextField txtModulo;
    @FXML
    private Label lblEvalIdioma1;
    @FXML
    private TextField textfEvalIdioma1;
    @FXML
    private TextField txtRespuesta;
    @FXML
    private TextField txtOpcion2;
    @FXML
    private TextField txtOpcion3;
    @FXML
    private TextField txtOpcion4;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Label lblEvalPregunta;
    @FXML
    private Label lblEvalop1;
    @FXML
    private Label lblEvalRespuesta;
    @FXML
    private Label lblEvalop2;
    @FXML
    private Label lblEvalop3;
    @FXML
    private Label lblEvalop4;
    
    @FXML
    public void switchForm(ActionEvent event){
        if(event.getSource()==btnStudent){
            formEstudiantes.setVisible(true);
            formEvaluaciones.setVisible(false);
            formExam.setVisible(false);
 
        }
        else if(event.getSource()==btnEval){
            formEstudiantes.setVisible(false);
            formEvaluaciones.setVisible(true);
            formExam.setVisible(false);
        }
        else if(event.getSource()==btnExam){
            formEstudiantes.setVisible(false);
            formEvaluaciones.setVisible(false);
            formExam.setVisible(true);
        }
    }
    
    public void initialize() {
         // Configurar las columnas
        colBPModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
        colBPPregunta.setCellValueFactory(new PropertyValueFactory<>("contenido"));
        colBPop1.setCellValueFactory(new PropertyValueFactory<>("alternativa1"));
        colBPop2.setCellValueFactory(new PropertyValueFactory<>("alternativa2"));
        colBPop3.setCellValueFactory(new PropertyValueFactory<>("alternativa3"));
        colBPop4.setCellValueFactory(new PropertyValueFactory<>("alternativa4"));
        colBPRespuesta.setCellValueFactory(new PropertyValueFactory<>("respuestaCorrecta"));

        // Cargar datos en el TableView
        
        ObservableList<BancoPregunta> listaPreguntas = bancoDAO.obtenerBancoPregunta();
        System.out.println("Registros obtenidos: " + listaPreguntas.size());
        tableEval.setItems(listaPreguntas);
        txtModulo.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) { // Solo permite números
                return change;
            }
            return null;
        }));
    }
        
    @FXML
    private void registrarPregunta() {
        BancoPregunta preguntaSeleccionada = tableEval.getSelectionModel().getSelectedItem();

        if (preguntaSeleccionada != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Añadir pregunta al examen");
            alert.setContentText("¿Desea añadir esta pregunta al examen?");

            // Mostrar y esperar la respuesta del usuario
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                agregarPreguntaATab(preguntaSeleccionada);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Ninguna pregunta seleccionada");
            alert.setContentText("Por favor seleccione una pregunta de la tabla.");
            alert.showAndWait();
        }
    }


    private int contadorPreguntas = 0;
   

private void agregarPreguntaATab(BancoPregunta pregunta) {
    // Crear un VBox para la pregunta y sus alternativas
    VBox preguntaBox = new VBox(10);
    preguntaBox.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 15; "
            + "-fx-border-color: #cccccc; -fx-border-radius: 5;");

    // Crear el Label para mostrar la pregunta
    Label lblPregunta = new Label("Pregunta " + (contadorPreguntas + 1) + ": " + pregunta.getContenido());
    lblPregunta.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333333;");

    // Crear CheckBoxes para las alternativas
    CheckBox chkOpcion1 = new CheckBox("A: " + pregunta.getAlternativa1());
    CheckBox chkOpcion2 = new CheckBox("B: " + pregunta.getAlternativa2());
    CheckBox chkOpcion3 = new CheckBox("C: " + pregunta.getAlternativa3());
    CheckBox chkOpcion4 = new CheckBox("D: " + pregunta.getAlternativa4());

    // Estilo a CheckBoxes
    chkOpcion1.setStyle("-fx-padding: 5;");
    chkOpcion2.setStyle("-fx-padding: 5;");
    chkOpcion3.setStyle("-fx-padding: 5;");
    chkOpcion4.setStyle("-fx-padding: 5;");

    // Añadir los elementos al VBox
    preguntaBox.getChildren().addAll(lblPregunta, chkOpcion1, chkOpcion2, chkOpcion3, chkOpcion4);

    // Obtener el Tab actual
    Tab tabActual = obtenerOcrearTab();
    AnchorPane anchorPane = (AnchorPane) tabActual.getContent();

    // Seleccionar el VBox adecuado (izquierdo o derecho) dentro del AnchorPane
    VBox targetVBox = (VBox) anchorPane.getChildren().get(contadorPreguntas % 2);
    targetVBox.getChildren().add(preguntaBox);

    contadorPreguntas++;
}

private Tab obtenerOcrearTab() {
    // Calcular la pestaña actual (cada pestaña soporta 2 preguntas)
    int indiceTab = contadorPreguntas / 2;

    // Si el TabPane ya tiene la pestaña, la selecciona
    if (tabPaneExam.getTabs().size() > indiceTab) {
        return tabPaneExam.getTabs().get(indiceTab);
    }

    // Si no existe, crea una nueva pestaña
    Tab nuevoTab = new Tab("PREGUNTA " + (indiceTab * 2 + 1) + "-" + (indiceTab * 2 + 2));
    nuevoTab.setClosable(false);

    // Crear un AnchorPane con 2 VBox (izquierda y derecha)
    AnchorPane anchorPane = new AnchorPane();

    VBox vBox1 = new VBox(10);
    vBox1.setLayoutX(14);
    vBox1.setLayoutY(14);
    vBox1.setPrefWidth(515);
    vBox1.setPrefHeight(302);
    vBox1.setStyle("-fx-background-color: #fff; -fx-border-color: #ccc; -fx-border-radius: 5;");

    VBox vBox2 = new VBox(10);
    vBox2.setLayoutX(549);
    vBox2.setLayoutY(14);
    vBox2.setPrefWidth(515);
    vBox2.setPrefHeight(302);
    vBox2.setStyle("-fx-background-color: #fff; -fx-border-color: #ccc; -fx-border-radius: 5;");

    anchorPane.getChildren().addAll(vBox1, vBox2);
    nuevoTab.setContent(anchorPane);

    tabPaneExam.getTabs().add(nuevoTab);
    return nuevoTab;
}


@FXML
private void insertarPregunta() {
    try {
        int idModuloInt = Integer.parseInt(txtModulo.getText());
        String contenido = txtPregunta.getText();
        String alternativa1 = txtOpcion1.getText();
        String alternativa2 = txtOpcion2.getText();
        String alternativa3 = txtOpcion3.getText();
        String alternativa4 = txtOpcion4.getText();
        String respuestaCorrecta = txtRespuesta.getText();
        String retroalimentacion = "Retroalimentación no asignada";

        // Llamar al método DAO para insertar
        bancoDAO.insertarPregunta(String.valueOf(idModuloInt), contenido, alternativa1,
                                         alternativa2, alternativa3, alternativa4, respuestaCorrecta, retroalimentacion);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Pregunta insertada correctamente.");

        // Actualizar la TableView
        cargarPreguntasEnTabla();

        // Limpiar los campos después de la inserción
        limpiarCampos();
    } catch (NumberFormatException e) {
        mostrarAlerta(Alert.AlertType.ERROR, "Error de formato", "El campo 'MÓDULO' debe ser un número entero.");
    }
}

private void cargarPreguntasEnTabla() {
    ObservableList<BancoPregunta> listaPreguntas = bancoDAO.obtenerBancoPregunta();
    tableEval.setItems(listaPreguntas);
}


    @FXML
    private void actualizarTabla() {
        // Lógica para recargar la tabla (similar a tu método inicializador de la tabla)
        System.out.println("Actualizar tabla: implementar lógica de actualización.");
    }

    private void limpiarCampos() {
        txtModulo.clear();
        txtPregunta.clear();
        txtOpcion1.clear();
        txtOpcion2.clear();
        txtOpcion3.clear();
        txtOpcion4.clear();
        txtRespuesta.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
   
}
