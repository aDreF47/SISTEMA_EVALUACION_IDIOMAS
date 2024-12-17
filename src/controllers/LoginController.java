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

import javafx.scene.Node;


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
        String emailOUsuario  = txtEmail.getText().trim(); 
        String password = txtPassword.getText().trim();
        
        if (emailOUsuario .isEmpty() || password.isEmpty()) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Por favor complete todos los campos.");
            return;
        }
        Usuario usuario = usuarioDAO.validarYObtenerUsuario(emailOUsuario , password);
        if (usuario == null) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Usuario o contraseña incorrectos.");
            return;
        }
        
        if (usuario.getEstado() == 0) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Actualmente su cuenta se encuaentra desactivado. comunicque con soporte");
            return;
        }
// Obtener el rol del usuario
        int idRol = usuarioDAO.validarRolUsuario(usuario.getDni());
        if (idRol == -1) {
            mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "No se pudo determinar el rol del usuario.");
            return;
        }

        // Guardar el usuario en sesión
        SesionUsuario.getInstancia().setUsuarioActual(usuario);

        // Cargar la vista correspondiente
        FXMLLoader loader;
        Parent root;
        String style = "";
        String titulo = "";

        switch (idRol) {
            case 1:
                loader = new FXMLLoader(getClass().getResource("/views/AdministrativoView.fxml"));
                root = loader.load();
                style = getClass().getResource("/styles/styleAdministrativo.css").toExternalForm();
                titulo = "Administrativo";
                break;
            case 2:
                loader = new FXMLLoader(getClass().getResource("/views/DocenteView.fxml"));
                root = loader.load();
                style = getClass().getResource("/styles/styleDocente.css").toExternalForm();
                titulo = "Docente";
                break;
            case 3:
                loader = new FXMLLoader(getClass().getResource("/views/ClienteView.fxml"));
                root = loader.load();
                // Obtener el controlador de la vista ClienteView
                ClienteController clienteController = loader.getController();
                clienteController.cargarDatosUsuario();
                style = getClass().getResource("/styles/styleCliente.css").toExternalForm();
                titulo = "Cliente";
                
                break;
            default:
                mostrarMensajeAlerta(Alert.AlertType.ERROR, "Error", "Rol no reconocido.");
                return;
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(style);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.centerOnScreen();
        stage.show();
        
    }   
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
    private void btnSignin(ActionEvent event) throws IOException {
       
        // Cargar la vista de registro
        Parent registrarView = FXMLLoader.load(getClass().getResource("/views/RegistrarView.fxml"));
        Scene registrarScene = new Scene(registrarView);

        // Aplicar la hoja de estilos
        registrarScene.getStylesheets().add(getClass().getResource("/styles/styleRegistrar.css").toExternalForm());

        // Obtener el escenario actual y cambiar la escena
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(registrarScene);
        window.setTitle("Registrar");
        window.centerOnScreen();
        window.show();
    }       
}
