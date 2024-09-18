package netControlListeners;

import events.KuchenEinfuegenEventImpl;
import events.KuchenEinfuegenEventListener;
import tcp.TCPClientImpl;

public class NetEinfuegenEventListener implements KuchenEinfuegenEventListener {
    private final TCPClientImpl tcp_client;

    public NetEinfuegenEventListener(TCPClientImpl tcpClient) {
        this.tcp_client = tcpClient;
    }

    @Override
    public void onKuchenEinfuegen(KuchenEinfuegenEventImpl event) throws Exception {
        tcp_client.senden('c', event);
        // nur zum testen:
        // System.out.println("Einfuegenevent verschickt");
    }
}
