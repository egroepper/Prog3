import automat.Verkaufsautomat;
import cli.Benutzeroberflaeche;
import cliControlListener.CLIListeRueckwegEventListener;
import dlcListeners.dlAuflistenEventListener;
import dlcListeners.dlEinfuegenEventListener;
import dlcListeners.dlEntfernenEventListener;
import dlcListeners.dlInspizierenEventListener;
import gui.Controller;
import handlers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* zum Testen copy paste:
Apfel 4.50 400 48 Gluten
Birne 4.50 400 48 Gluten
Erdbeere 4.50 400 48 Gluten
Zitrone 4.50 400 48 Gluten
*/

public class MainCLIundGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/GUIVerkaufsautomat.fxml"));
        Parent root = loader.load();
        stage.setTitle("Verkaufsautomat");
        stage.setScene(new Scene(root));
        stage.show();

        int kapazitaet = 20;
        if (getParameters().getRaw().size() > 0) { // Quelle: https://www.tabnine.com/code/java/methods/javafx.application.Application$Parameters/getRaw
            kapazitaet = Integer.parseInt(getParameters().getRaw().get(0));
        }
        Verkaufsautomat vkaParallel = new Verkaufsautomat(kapazitaet);
        Benutzeroberflaeche cli = new Benutzeroberflaeche();

        // r Rueckweg
        ListeRueckwegEventHandler rueckwegEventHandler = new ListeRueckwegEventHandler();
        rueckwegEventHandler.add(new CLIListeRueckwegEventListener(cli));

        // CRUD Hinweg
        // C
        KuchenEinfuegenEventHandler einfuegenEventHandler = new KuchenEinfuegenEventHandler();
        einfuegenEventHandler.add(new dlEinfuegenEventListener(vkaParallel));

        // R
        KuchenAuflistenEventHandler auflistenEventHandler = new KuchenAuflistenEventHandler();
        auflistenEventHandler.add(new dlAuflistenEventListener(vkaParallel, rueckwegEventHandler));

        // U
        KuchenInspizierenEventHandler inspizierenEventHandler = new KuchenInspizierenEventHandler();
        inspizierenEventHandler.add(new dlInspizierenEventListener(vkaParallel));

        // D
        KuchenEntfernenEventHandler entfernenEventHandler = new KuchenEntfernenEventHandler();
        entfernenEventHandler.add(new dlEntfernenEventListener(vkaParallel));

        cli.setKuchenEinfuegenEventHandler(einfuegenEventHandler);
        cli.setKuchenAuflistenEventHandler(auflistenEventHandler);
        cli.setKuchenInspizierenEventHandler(inspizierenEventHandler);
        cli.setKuchenEntfernenEventHandler(entfernenEventHandler);


        Controller gui = loader.getController();

        gui.setKuchenEinfuegenEventHandler(einfuegenEventHandler);
        gui.setKuchenAuflistenEventHandler(auflistenEventHandler);
        gui.setKuchenInspizierenEventHandler(inspizierenEventHandler);
        gui.setKuchenEntfernenEventHandler(entfernenEventHandler);

        gui.setVerkaufsautomat(vkaParallel);

        Thread cliThread = new Thread(cli::cli); // hier Vorschlag von Intellij angenommen
        cliThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
    }
