import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* zum testen copy paste:
Apfel 4.50 400 48 Gluten
Birne 4.50 400 48 Gluten
Erdbeere 4.50 400 48 Gluten
Zitrone 4.50 400 48 Gluten
*/

// Quelle: // How to install Scene Builder and use it with JavaFX and IntelliJ [2022] https://youtu.be/Nzwx5k2kWJw?feature=shar
public class MainGUI  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/GUIVerkaufsautomat.fxml"));
        Parent root = loader.load();
        stage.setTitle("Verkaufsautomat");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
