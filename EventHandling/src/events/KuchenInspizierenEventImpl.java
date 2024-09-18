package events;

import java.util.EventObject;

public class KuchenInspizierenEventImpl extends EventObject {
    private final int fachnummer;
    public KuchenInspizierenEventImpl(Object source, int fachnummer) {
        super(source);
        this.fachnummer = fachnummer;
    }

    public int getFachnummer() {
        return fachnummer;
    }
}
