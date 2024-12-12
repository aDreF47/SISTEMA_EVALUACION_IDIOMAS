/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;

/**
 *
 * @author nando
 */
public class LoginController {

    
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private void btnLongin(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        System.out.println(email);
        
        abrirVentana("/views/ClienteView.fxml", "Cliente");
        // Verificar credenciales y acceder
        /*if (sistema.verificarAcceso(email, password)) {
            System.out.println("Acceso concedido.");
            
            // Redirigir a la vista correspondiente
            Usuario usuarioActual = sistema.obtenerPersonaPorCorreo(email);
            
            // Inicia la sesión estableciendo el usuario actual en la instancia de Sesion
            Sesion.getInstancia().iniciarSesion(usuarioActual);
            if (usuarioActual instanceof Estudiante) {
                abrirVentana("/views/EstudianteView.fxml", "Estudiante");
            } else if (usuarioActual instanceof Docente) {
                abrirVentana("/views/DocenteView.fxml", "Docente");
            }

        } else {
            System.out.println("Acceso denegado. Usuario o contraseña incorrectos.");
        }*/
    }
    private void abrirVentana(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Crea un nuevo Stage para la nueva vista
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle(titulo);
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.centerOnScreen();
            nuevaVentana.show();

            // Cierra la ventana actual de inicio de sesión
            Stage loginStage = (Stage) txtEmail.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void btnSignin(ActionEvent event) {
    }
    
}
