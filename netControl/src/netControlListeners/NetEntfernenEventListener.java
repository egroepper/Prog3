package netControlListeners;

import events.KuchenEntfernenEventImpl;
import events.KuchenEntfernenEventListener;
import tcp.TCPClientImpl;

public class NetEntfernenEventListener implements KuchenEntfernenEventListener {
    private final TCPClientImpl tcp_client;

    public NetEntfernenEventListener(TCPClientImpl tcpClient) {
        this.tcp_client = tcpClient;
    }

    @Override
    public void onKuchenEntfernen(KuchenEntfernenEventImpl event) {
        tcp_client.senden('d', event);
        // nur zum testen:
        // System.out.println("Entfernenevent verschickt");
    }
}
