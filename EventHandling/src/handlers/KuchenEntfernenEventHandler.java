package handlers;

import events.KuchenEntfernenEventImpl;
import events.KuchenEntfernenEventListener;

import java.util.LinkedList;
import java.util.List;

public class KuchenEntfernenEventHandler {
    private final List<KuchenEntfernenEventListener> entfernenEventListeners = new LinkedList<>();
    public void add(KuchenEntfernenEventListener listener) {
        entfernenEventListeners.add(listener);
    }
    public void remove(KuchenEntfernenEventListener listener) {
        entfernenEventListeners.remove(listener);
    }

    public void handle(KuchenEntfernenEventImpl event) {
        for (KuchenEntfernenEventListener listener : entfernenEventListeners) {
            listener.onKuchenEntfernen(event);
        }
    }
}
