package netControlListeners;

import events.ListeRueckwegEventImpl;
import events.ListeRueckwegEventListener;
import tcp.TCPServerImpl;

public class NetRueckwegAuflistenEventListener implements ListeRueckwegEventListener {
    private final TCPServerImpl tcp_server;

    public NetRueckwegAuflistenEventListener(TCPServerImpl tcpServer) {
        this.tcp_server = tcpServer;
    }

    @Override
    public void onListeRueckwegEvent(ListeRueckwegEventImpl event) {
        tcp_server.senden('o', event);
        // nur zum testen:
        // System.out.println("Rueckwegevent verschickt");
    }
}
