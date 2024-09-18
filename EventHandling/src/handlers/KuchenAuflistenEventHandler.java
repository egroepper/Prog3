package handlers;

import events.KuchenAuflistenEventImpl;
import events.KuchenAuflistenEventListener;

import java.util.LinkedList;
import java.util.List;

public class KuchenAuflistenEventHandler {
    private final List<KuchenAuflistenEventListener> auflistenEventListeners = new LinkedList<>();

    public void add(KuchenAuflistenEventListener listener) {
        auflistenEventListeners.add(listener);
    }

    public void remove(KuchenAuflistenEventListener listener) {
        auflistenEventListeners.remove(listener);
    }

    public void handle(KuchenAuflistenEventImpl event) {
        for (KuchenAuflistenEventListener listener : auflistenEventListeners) {
            listener.onKuchenAuflisten(event);
        }
    }
}
