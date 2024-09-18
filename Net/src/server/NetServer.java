package server;

import java.io.IOException;

// Wahl eines Interfaces, damit es auch für UDP genutzt werden kann
public interface NetServer {

    void senden(char zeichen, Object event);

    void zeichenLesen() throws IOException;
}
