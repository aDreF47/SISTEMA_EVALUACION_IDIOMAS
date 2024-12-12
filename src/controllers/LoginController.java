/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.UsuarioDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    
    private void mostrarMensajeAlerta(Alert.AlertType tipo,String titulo, String message) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void btnLogin(ActionEvent event) throws IOException {
        
        if (usuarioDAO == null) {
        usuarioDAO = new UsuarioDAO();
        }
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        System.out.println(email);
        
        if (email.isEmpty() || password.isEmpty()) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Por favor complete todos los campos.");
            System.out.println("Hola1");
            return;
        }
        System.out.println(email);
        System.out.println(password);
        Usuario usuario = usuarioDAO.validarYObtenerUsuario(email, password);
        if (usuario == null) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Usuario o contraseña incorrectos.");
            System.out.println("hola2");
            return;
        }
        
        System.out.println("hola3");
                 
        mostrarMensajeAlerta(Alert.AlertType.CONFIRMATION, "Alert", "Bienvenido ");

        // Cargar la vista correspondiente según el tipo de usuario
        FXMLLoader loader;
        Parent root;
        Scene scene=null;
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        if (usuario.isEstado() == 1) {
            // Cargar la vista de Cliente
            loader = new FXMLLoader(getClass().getResource("/views/ClienteView.fxml"));
            root = loader.load();
            //ClienteController cC = loader.getController();
            //cC.colocarNombreCompleto(userName);
            scene = new Scene(root);
            window.setTitle("Cliente");
        } else if (usuario.isEstado() == 0) {
           mostrarMensajeAlerta(Alert.AlertType.ERROR, "Alert", "Por favor Registrarse.");             
        }

        // Mostrar la nueva escena
        window.setScene(scene);
        window.show();
        //abrirVentana("/views/ClienteView.fxml", "Cliente");
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
    /*private void abrirVentana(String fxmlPath, String titulo) {
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
    }*/
    @FXML
    private void btnSignin(ActionEvent event) {
    }  
       
}
