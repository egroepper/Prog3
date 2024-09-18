import cli.Benutzeroberflaeche;
import cliControlListener.CLIListeRueckwegEventListener;
import handlers.*;
import netControlListeners.NetAuflistenEventListener;
import netControlListeners.NetEinfuegenEventListener;
import netControlListeners.NetEntfernenEventListener;
import netControlListeners.NetInspizierenEventListener;
import tcp.TCPClientImpl;

/* zum testen copy paste:
Apfel 4.50 400 48 Gluten
Birne 4.50 400 48 Gluten
Erdbeere 4.50 400 48 Gluten
Zitrone 4.50 400 48 Gluten
*/
public class MainClientTCP {
    public static void main(String[] args) {
        TCPClientImpl tcpClient = new TCPClientImpl(4711);
        Benutzeroberflaeche bof = new Benutzeroberflaeche();

        // C
        KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler = new KuchenEinfuegenEventHandler();
        bof.setKuchenEinfuegenEventHandler(kuchenEinfuegenEventHandler);
        kuchenEinfuegenEventHandler.add(new NetEinfuegenEventListener(tcpClient));

        // R
        KuchenAuflistenEventHandler kuchenAuflistenEventHandler = new KuchenAuflistenEventHandler();
        bof.setKuchenAuflistenEventHandler(kuchenAuflistenEventHandler);
        kuchenAuflistenEventHandler.add(new NetAuflistenEventListener(tcpClient));

        // R Rueckweg
        ListeRueckwegEventHandler listeRueckwegEventHandler = new ListeRueckwegEventHandler();
        tcpClient.setListeRueckwegEventHandler(listeRueckwegEventHandler);
        listeRueckwegEventHandler.add(new CLIListeRueckwegEventListener(bof));

        // U
        KuchenInspizierenEventHandler kuchenInspizierenEventHandler = new KuchenInspizierenEventHandler();
        bof.setKuchenInspizierenEventHandler(kuchenInspizierenEventHandler);
        kuchenInspizierenEventHandler.add(new NetInspizierenEventListener(tcpClient));

        // D
        KuchenEntfernenEventHandler kuchenEntfernenEventHandler = new KuchenEntfernenEventHandler();
        bof.setKuchenEntfernenEventHandler(kuchenEntfernenEventHandler);
        kuchenEntfernenEventHandler.add(new NetEntfernenEventListener(tcpClient));

        bof.cli();
    }
}
