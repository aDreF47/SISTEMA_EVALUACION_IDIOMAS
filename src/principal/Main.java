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

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DocenteView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root); // Scena fundamentl para FXs

        scene.getStylesheets().add(getClass().getResource("/styles/styleLogin.css").toExternalForm());

        // Centrar la ventana
        primaryStage.centerOnScreen();

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene); // muestra la escena internamente
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
