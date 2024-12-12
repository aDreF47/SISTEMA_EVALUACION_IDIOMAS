package principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginVew.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root); //Scena fundamentl para FXs
        
        scene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());
        
        
        // Establece el tamaño mínimo de la ventana
        //primaryStage.setMinWidth(386.0);
        //primaryStage.setMinHeight(633.0);

        // Establece el tamaño inicial de la ventana
        //primaryStage.setWidth(1280.0);
        //primaryStage.setHeight(720.0);

        // Centrar la ventana
        primaryStage.centerOnScreen();
        
        primaryStage.setTitle("Login"); 
        primaryStage.setScene(scene); // muestra la escena internamente
        primaryStage.show();
        
            
    }

    public static void main(String[] args) {
        launch(args);
    }
    /*@Override
    public void start(Stage primaryStage) throws Exception {
        String a = "PagoView";
        String b = "ClienteView";
        String c = "ExamenView";
        String d = "RetroalimentacionView";
        String f = "LoginVew";
        Parent root1 = FXMLLoader.load(getClass().getResource("/views/"+f+".fxml"));
        primaryStage.setTitle("Sistema de Evaluación de Idiomas - Login");
        primaryStage.setScene(new Scene(root1));
        primaryStage.show();

    }*/
}
