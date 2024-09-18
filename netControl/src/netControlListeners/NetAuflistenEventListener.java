package netControlListeners;

import events.KuchenAuflistenEventImpl;
import events.KuchenAuflistenEventListener;
import tcp.TCPClientImpl;

public class NetAuflistenEventListener  implements KuchenAuflistenEventListener {
    private final TCPClientImpl tcp_client;

    public NetAuflistenEventListener(TCPClientImpl tcpClient) {
        this.tcp_client = tcpClient;
    }

    @Override
    public void onKuchenAuflisten(KuchenAuflistenEventImpl event) {
        tcp_client.auflisten('r', event);
        // nur zum testen:
        // System.out.println("Auflistenevent verschickt");
    }
}
