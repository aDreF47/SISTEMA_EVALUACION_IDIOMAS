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
    private Label lblEvalIdioma;
    @FXML
    private TextField textfEvalIdioma;
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
    private Button btnModEval;
    @FXML
    private Button btnElimEval;
    @FXML
    private TableColumn<?, ?> colEstC1;
    @FXML
    private TableColumn<?, ?> colEstC2;
    @FXML
    private AnchorPane formEstudiantes;
    @FXML
    private AnchorPane formEvaluaciones;
    @FXML
    private Label lblEvalMod;
    @FXML
    private TextField textfEvalMod;
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
/*private Tab obtenerTabActual() {
    // Devuelve el Tab seleccionado actualmente en el TabPane
    return tabPaneExam.getSelectionModel().getSelectedItem();
}*/
  
   /*private void agregarPreguntaATab(BancoPregunta pregunta) {
       // Cada Tab tendrá máximo 2 preguntas
       if (contadorPreguntas % 2 == 0) {
           tabP1_2 = new Tab("Preguntas " + (contadorPreguntas + 1) + "-" + (contadorPreguntas + 2));
           tabPaneExam.getTabs().add(tabP1_2);
       }

       // Crear contenido de la pregunta
       VBox preguntaBox = new VBox(10);
       //preguntaBox.setPadding(new Insets(10));
       preguntaBox.setPadding(new Insets(10, 10, 10, 10)); // Top, Right, Bottom, Left

       preguntaBox.setStyle("-fx-background-color: #f2f2f2; -fx-border-color: #cccccc;");

       Label lblPregunta = new Label("Pregunta: " + pregunta.getContenido());
       Label lblOpcion1 = new Label("A) " + pregunta.getAlternativa1());
       Label lblOpcion2 = new Label("B) " + pregunta.getAlternativa2());
       Label lblOpcion3 = new Label("C) " + pregunta.getAlternativa3());
       Label lblOpcion4 = new Label("D) " + pregunta.getAlternativa4());
       Label lblRespuesta = new Label("Respuesta Correcta: " + pregunta.getRespuestaCorrecta());
       lblRespuesta.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

       // Agregar los elementos al contenedor
       preguntaBox.getChildren().addAll(lblPregunta, lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4, lblRespuesta);

       // Agregar contenido al tab actual
       if (tabP1_2.getContent() == null) {
           tabP1_2.setContent(new VBox(preguntaBox));
       } else {
           VBox existingContent = (VBox) tabP1_2.getContent();
           existingContent.getChildren().add(preguntaBox);
       }

       contadorPreguntas++;
   }
   
    /*private ObservableList<BancoPregunta> listaPreguntas;  // Lista principal de preguntas
    private FilteredList<BancoPregunta> listaFiltrada;  // Lista filtrada
        
    
    
    
    public void metodobuttonSearch() {
        // Cargar los datos de la base de datos
        listaPreguntas = bancoDAO.obtenerBancoPregunta();

        // Inicializar la lista filtrada
        listaFiltrada = new FilteredList<>(listaPreguntas, p -> true);

        // Configurar TableView con la lista filtrada
        tableEval.setItems(listaFiltrada);

        // Agregar filtro en tiempo real
        testfEvalSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            listaFiltrada.setPredicate(pregunta -> {
                if (newValue == null || newValue.isEmpty()) {
                        return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return pregunta.getContenido().toLowerCase().contains(lowerCaseFilter);
            });
        });
    }*/
   
}
