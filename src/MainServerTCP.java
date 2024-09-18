/* zum testen copy paste:
Apfel 4.50 400 48 Gluten
Birne 4.50 400 48 Gluten
Erdbeere 4.50 400 48 Gluten
Zitrone 4.50 400 48 Gluten
*/

import automat.Verkaufsautomat;
import dlcListeners.dlAuflistenEventListener;
import dlcListeners.dlEinfuegenEventListener;
import dlcListeners.dlEntfernenEventListener;
import dlcListeners.dlInspizierenEventListener;
import handlers.*;
import netControlListeners.NetRueckwegAuflistenEventListener;
import tcp.TCPServerImpl;

public class MainServerTCP {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            int kapazitaet = Integer.parseInt(args[0]);
            Verkaufsautomat vka = new Verkaufsautomat(kapazitaet);

            TCPServerImpl tcpServer = new TCPServerImpl();

            // C
            KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler = new KuchenEinfuegenEventHandler();
            tcpServer.setKuchenEinfuegenEventHandler(kuchenEinfuegenEventHandler);
            dlEinfuegenEventListener dlEinfuegenEventListener = new dlEinfuegenEventListener(vka);
            kuchenEinfuegenEventHandler.add(dlEinfuegenEventListener);

            // R Hinweg
            KuchenAuflistenEventHandler kuchenAuflistenEventHandler = new KuchenAuflistenEventHandler();
            tcpServer.setKuchenAuflistenEventHandler(kuchenAuflistenEventHandler);

            // R Rueckweg
            ListeRueckwegEventHandler listeRueckwegEventHandler = new ListeRueckwegEventHandler();
            listeRueckwegEventHandler.add(new NetRueckwegAuflistenEventListener(tcpServer));
            kuchenAuflistenEventHandler.add(new dlAuflistenEventListener(vka, listeRueckwegEventHandler));

            // U
            KuchenInspizierenEventHandler kuchenInspizierenEventHandler = new KuchenInspizierenEventHandler();
            tcpServer.setKuchenInspizierenEventHandler(kuchenInspizierenEventHandler);
            kuchenInspizierenEventHandler.add(new dlInspizierenEventListener(vka));

            // D
            KuchenEntfernenEventHandler kuchenEntfernenEventHandler = new KuchenEntfernenEventHandler();
            tcpServer.setKuchenEntfernenEventHandler(kuchenEntfernenEventHandler);
            kuchenEntfernenEventHandler.add(new dlEntfernenEventListener(vka));

            new Thread(tcpServer).start();
        }
    }
}
