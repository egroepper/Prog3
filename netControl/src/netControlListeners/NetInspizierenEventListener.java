package netControlListeners;

import events.KuchenInspizierenEventImpl;
import events.KuchenInspizierenEventListener;
import tcp.TCPClientImpl;

public class NetInspizierenEventListener implements KuchenInspizierenEventListener {
    private final TCPClientImpl tcp_Client;

    public NetInspizierenEventListener(TCPClientImpl tcpClient) {
        this.tcp_Client = tcpClient;
    }

    @Override
    public void onKuchenInspizieren(KuchenInspizierenEventImpl event) {
        tcp_Client.senden('u', event);
        // nur zum testen:
        // System.out.println("Inspizierenevent verschickt");
    }
}
