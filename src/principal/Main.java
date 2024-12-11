package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String a = "PagoView";
        String b = "ClienteView";
        String c = "ExamenView";
        String d = "RetroalimentacionView";
        Parent root1 = FXMLLoader.load(getClass().getResource("/views/"+b+".fxml"));
        primaryStage.setTitle("Sistema de Evaluación de Idiomas - Pagos");
        primaryStage.setScene(new Scene(root1));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
