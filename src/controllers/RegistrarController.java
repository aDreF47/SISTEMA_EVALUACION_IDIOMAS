/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.EstudianteDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Usuario;

/**
 * FXML Controller class
 *
 * @author JuniorG
 */
public class RegistrarController {

    @FXML
    private StackPane rootPane;
    @FXML
    private VBox panelDatos;
    @FXML
    private TextField txtNombreRegistro;
    @FXML
    private TextField txtDocumentoRegistro;
    @FXML
    private TextField txtCorreoRegistro;
    @FXML
    private VBox panelCrearcuenta;
    @FXML
    private TextField txtUsuarioRegistro;
    @FXML
    private PasswordField txtContraseñaRegistro;
    @FXML
    private PasswordField txtContraseñaConfirmadcionRegistro;
    @FXML
    private CheckBox chckTerminosYCondiciones;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnSalirRegistro;
    @FXML
    private TextField txtApellidoRegistro;
    
    UsuarioDAO userDAO = new UsuarioDAO(); 
    //EstudianteDAO estDAO = new EstudianteDAO();
    

    public void initialize() {
       
    }    

    private boolean validarNombre(String nombre) {
        return validarCampo(nombre);
    }
    private boolean validarApellido(String apellido) {
        return validarCampo(apellido);
    }
    private boolean validarCampo(String valor) {
        if (valor.length() < 2 || valor.length() > 50) {
            return false;
        }
        return valor.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\\s]+");
    }
    
    private boolean validarNumeroDocumento() {
        // Obtener el número de documento ingresado
        String numeroDocumento = txtDocumentoRegistro.getText().trim();

        // Validar que el DNI tenga exactamente 8 dígitos
        if (numeroDocumento.length() != 8) {
            mostrarMensajeError("El DNI debe tener exactamente 8 caracteres.");
            return false;
        }
        // Validar que el DNI contenga solo números
        if (!numeroDocumento.matches("\\d+")) {
            mostrarMensajeError("El DNI debe contener solo números.");
            return false;
        }
        return true;
    }
    private boolean esCorreoValido() {
        String email = txtCorreoRegistro.getText().trim();

        if (email.length() < 6 || email.length() > 50) {
            mostrarMensajeError("La dirección de correo electrónico debe tener entre 6 y 50 caracteres.");
            return false;
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            mostrarMensajeError("Por favor, introduce una dirección de correo electrónico válida.");
            return false;
        }
        return true;
    }
                
    private boolean datosLLenos() {
        String nom = txtNombreRegistro.getText().trim();
        String apell = txtApellidoRegistro.getText().trim();
        String doc = txtDocumentoRegistro.getText().trim();
        String email = txtCorreoRegistro.getText().trim();
        String user = txtUsuarioRegistro.getText().trim();
        String contra = txtContraseñaRegistro.getText().trim();
        String ccontra = txtContraseñaConfirmadcionRegistro.getText().trim();
        

        if (nom.isEmpty() || apell.isEmpty() || doc.isEmpty() || email.isEmpty() 
            || user.isEmpty() || contra.isEmpty() || ccontra.isEmpty()) {
            mostrarMensajeError("Por favor llene los datos obligatorios*");
            
            
            return false;
        }

        System.out.println("Datos obligatorios llenos con éxito");
        return true;
    } 
    private boolean datosUnicos(){
        if(userDAO.validateDocumentoUnico(txtDocumentoRegistro.getText())){
            mostrarMensajeError("documento ya existe");
            return false;
        }
        if(userDAO.validarCorreoUnico(txtCorreoRegistro.getText())){
            mostrarMensajeError("correo ya existe");
            return false;
        }
        return true;
    }    

    private boolean validarContraseña(String contraseña, String confirmacion) {
        if (contraseña.length() < 6 || contraseña.length() > 20) {
            mostrarMensajeError("La contraseña debe tener entre 6 y 20 caracteres.");
            return false;
        }
        if (!contraseña.equals(confirmacion)) {
            mostrarMensajeError("Las contraseñas no coinciden.");
            return false;
        }
        return true;
    }
    @FXML
    private void chbxTerminosAction(ActionEvent event) {
    }

    @FXML
    private void btnRegistrarAction(ActionEvent event) {
        if(txtContraseñaRegistro.getText().equals(txtContraseñaConfirmadcionRegistro.getText())){
            if (confirmarRegistro("Alerta", "¿Seguro que deseas registrarte?")) {
                // Si el usuario confirma, cargar la vista de login
                Usuario cliente = new Usuario();
                // Asignar los valores correspondientes de los controles a los atributos del cliente
                cliente.setNombre(txtNombreRegistro.getText());
                cliente.setApellido(txtApellidoRegistro.getText());
                cliente.setDni(txtDocumentoRegistro.getText());
                cliente.setEmail(txtCorreoRegistro.getText());
                cliente.setUsuario(txtUsuarioRegistro.getText());
                cliente.setContrasena(txtContraseñaRegistro.getText());
             
               
                // Intentar registrar al cliente en la base de datos
                boolean registroExitoso = userDAO.registrarCliente(cliente);

                 if (registroExitoso) {
                    // Si el registro fue exitoso, cargar la vista de inicio de sesión
                    try {
                        Stage stage = (Stage) btnRegistrar.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        // Manejar cualquier error al cargar la vista de inicio de sesión
                        e.printStackTrace();
                    }
                } else {
                    // Si el registro falla, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(null, "Error al registrar el cliente. Por favor, inténtelo de nuevo.");
                }
            }
        } else{
            mostrarMensajeError("La contraseña no coinciden");
            limpiarCampo(txtContraseñaConfirmadcionRegistro);
        }
        
    }
    private void limpiarCampo(TextField campo) {
        campo.setText("");
    }

    @FXML
    private void btnSalirRegistroAction(ActionEvent event) throws IOException {
        // Cargar la vista de registro
        Parent registrarView = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
        Scene registrarScene = new Scene(registrarView);

        // Aplicar la hoja de estilos
        registrarScene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());

        // Obtener el escenario actual y cambiar la escena
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(registrarScene);
        window.setTitle("Login");
        window.centerOnScreen();
        window.show();
    }
    private void mostrarMensajeError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean confirmarRegistro(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //tipo de alerta
        alert.setTitle(title); //ttulo de la ventana
        alert.setHeaderText(null); // borra lo deafults horrible
        alert.setContentText(message); //muestra el mensaje
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }
}
