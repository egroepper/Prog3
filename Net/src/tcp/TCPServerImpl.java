package tcp;

import events.KuchenAuflistenEventImpl;
import events.KuchenEinfuegenEventImpl;
import events.KuchenEntfernenEventImpl;
import events.KuchenInspizierenEventImpl;
import handlers.KuchenAuflistenEventHandler;
import handlers.KuchenEinfuegenEventHandler;
import handlers.KuchenEntfernenEventHandler;
import handlers.KuchenInspizierenEventHandler;
import server.NetServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerImpl implements Runnable, NetServer {

    private KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler;
    private KuchenAuflistenEventHandler kuchenAuflistenEventHandler;
    private KuchenInspizierenEventHandler kuchenInspizierenEventHandler;
    private KuchenEntfernenEventHandler kuchenEntfernenEventHandler;

    public void setKuchenEinfuegenEventHandler(KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler) {
        this.kuchenEinfuegenEventHandler = kuchenEinfuegenEventHandler;
    }
    public void setKuchenAuflistenEventHandler(KuchenAuflistenEventHandler kuchenAuflistenEventHandler) {
        this.kuchenAuflistenEventHandler = kuchenAuflistenEventHandler;
    }
    public void setKuchenInspizierenEventHandler(KuchenInspizierenEventHandler kuchenInspizierenEventHandler) {
        this.kuchenInspizierenEventHandler = kuchenInspizierenEventHandler;
    }
    public void setKuchenEntfernenEventHandler(KuchenEntfernenEventHandler kuchenEntfernenEventHandler) {
        this.kuchenEntfernenEventHandler = kuchenEntfernenEventHandler;
    }

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public TCPServerImpl() throws IOException{
        ServerSocket serverSocket = new ServerSocket(4711);
        this.socket = serverSocket.accept();
        System.out.println("mit Client verbunden");

        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }


    @Override
    public void zeichenLesen() throws IOException {
        try {
            char zeichen = this.objectInputStream.readChar();
            Object event = this.objectInputStream.readObject();

            switch (zeichen) {
                case 'c':
                    if (kuchenEinfuegenEventHandler != null) {
                        kuchenEinfuegenEventHandler.handle((KuchenEinfuegenEventImpl) event);
                    } break;
                case 'r':
                    if (kuchenAuflistenEventHandler != null) {
                        kuchenAuflistenEventHandler.handle((KuchenAuflistenEventImpl) event);
                    } break;
                case 'u':
                    if (kuchenInspizierenEventHandler != null) {
                        kuchenInspizierenEventHandler.handle((KuchenInspizierenEventImpl) event);
                    } break;
                case 'd':
                    if (kuchenEntfernenEventHandler != null) {
                        kuchenEntfernenEventHandler.handle((KuchenEntfernenEventImpl) event);
                    } break;
                default:
                    System.out.println("ungueltiges Zeichen");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                zeichenLesen();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void senden(char zeichen, Object event) {
        try {
            this.objectOutputStream.writeChar(zeichen);
            this.objectOutputStream.writeObject(event);
            this.objectOutputStream.flush();
            this.objectOutputStream.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
