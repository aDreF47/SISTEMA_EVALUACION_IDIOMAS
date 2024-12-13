/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author nando
 */
public class RespuestasController {

    @FXML
    private Label pregunta1;
    @FXML
    private Tab TAB1;
    @FXML
    private Label respuesta1;
    @FXML
    private Label pregunta2;
    @FXML
    private Label respuesta2;
    @FXML
    private Label pregunta3;
    @FXML
    private Label respuesta3;
    @FXML
    private Label pregunta4;
    @FXML
    private Label respuesta4;
    @FXML
    private Label retro3;
    @FXML
    private Label retro4;
    @FXML
    private Tab TAB2;
    @FXML
    private Label pregunta5;
    @FXML
    private Label pregunta6;
    @FXML
    private Label pregunta7;
    @FXML
    private Label pregunta8;
    @FXML
    private Tab TAB3;
    @FXML
    private Label pregunta9;
    @FXML
    private Label pregunta10;
    @FXML
    private Label rmarcada1;
    @FXML
    private Label rmarcada2;
    @FXML
    private Label comentarioRC1;
    @FXML
    private Label comentarioRC2;
    @FXML
    private Label rmarcada3;
    @FXML
    private Label rmarcada4;
    @FXML
    private Label comentarioRC3;
    @FXML
    private Label comentarioRC4;
    @FXML
    private Label comentarioRC5;
    @FXML
    private Label rmarcada5;
    @FXML
    private Label respuesta5;
    @FXML
    private Label comentarioRC6;
    @FXML
    private Label rmarcada6;
    @FXML
    private Label respuesta6;
    @FXML
    private Label comentarioRC7;
    @FXML
    private Label comentarioRC8;
    @FXML
    private Label rmarcada7;
    @FXML
    private Label respuesta7;
    @FXML
    private Label rmarcada8;
    @FXML
    private Label respuesta8;
    @FXML
    private Label comentarioRC9;
    @FXML
    private Label rmarcada9;
    @FXML
    private Label respuesta9;
    @FXML
    private Label comentarioRC10;
    @FXML
    private Label rmarcada10;
    @FXML
    private Label respuesta10;

    @FXML
    private TabPane tabPane;
    
    

    @FXML
    private void NEXT1(ActionEvent event) {
        tabPane.getSelectionModel().select(TAB2);
    }

    @FXML
    private void BACK1(ActionEvent event) {
        tabPane.getSelectionModel().select(TAB1);
    }

    @FXML
    private void NEXT2(ActionEvent event) {
        tabPane.getSelectionModel().select(TAB3);
    }

    @FXML
    private void BACK2(ActionEvent event) {
        tabPane.getSelectionModel().select(TAB2);
    }

    @FXML
    private void OK(ActionEvent event) {
        try {
        // Cargar la nueva vista (ClienteView.fxml).
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ClienteView.fxml"));
        Parent root = loader.load();

        // Crear una nueva ventana (Stage) para la vista.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Vista Cliente");
        stage.show();

        // Cerrar la ventana actual.
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    @FXML
    private void SALIR(ActionEvent event) {
        
        try {
        // Cargar la nueva vista (ClienteView.fxml).
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ClienteView.fxml"));
        Parent root = loader.load();

        // Crear una nueva ventana (Stage) para la vista.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Vista Cliente");
        stage.show();

        // Cerrar la ventana actual.
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    

    
    

}
