package client;

import java.util.EventObject;

// Wahl eines Interfaces, damit es auch für UDP genutzt werden kann
public interface NetClient {

    void senden (char zeichen, EventObject eventObject);

    void auflisten(char zeichen, EventObject eventObject);
}
