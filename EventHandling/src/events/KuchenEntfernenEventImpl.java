package events;

import java.util.EventObject;

public class KuchenEntfernenEventImpl extends EventObject {
    private final int fachnummer;

    public KuchenEntfernenEventImpl(Object source, int fachnummer) {
        super(source);
        this.fachnummer = fachnummer;
    }

    public int getFachnummer() {
        return fachnummer;
    }
}
