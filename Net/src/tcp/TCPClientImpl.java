package tcp;

import client.NetClient;
import events.ListeRueckwegEventImpl;
import handlers.ListeRueckwegEventHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.EventObject;

public class TCPClientImpl implements NetClient {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ListeRueckwegEventHandler listeRueckwegEventHandler;

    public void setListeRueckwegEventHandler(ListeRueckwegEventHandler listeRueckwegEventHandler) {
        this.listeRueckwegEventHandler = listeRueckwegEventHandler;
    }

    public TCPClientImpl(int port) {
        this.connectSocket(port);
    }

    private void connectSocket(int port) {
        try {
            this.socket = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("new client@" + socket.getInetAddress() + ":" + socket.getPort());
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void senden(char zeichen, EventObject eventObject) {
        try {
            if ((!socket.isConnected())) {
                throw new RuntimeException("Keine Verbindung, kann nicht gesendet werden");
            }
            this.outputStream.writeChar(zeichen);
            this.outputStream.writeObject(eventObject);
            this.outputStream.flush();
            this.outputStream.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void zeichenLesen() {
        try {
            char zeichen = this.inputStream.readChar();
            Object event = this.inputStream.readObject();
            if (zeichen == 'o') {
                if (listeRueckwegEventHandler != null) {
                    listeRueckwegEventHandler.handle((ListeRueckwegEventImpl) event);
                }
            } else {
                throw new IllegalStateException("ungueltiges Steuerungszeichen");
            }
        } catch (IOException | ClassNotFoundException e) { // hier Vorschlag von Intellij angenommen
            throw new RuntimeException(e);
        }
    }

    @Override
    public void auflisten(char zeichen, EventObject eventObject) {
        try {
            this.outputStream.writeChar(zeichen);
            this.outputStream.writeObject(eventObject);
            zeichenLesen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
