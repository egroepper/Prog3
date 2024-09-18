package handlers;

import events.KuchenInspizierenEventImpl;
import events.KuchenInspizierenEventListener;

import java.util.LinkedList;
import java.util.List;

public class KuchenInspizierenEventHandler {
    private final List<KuchenInspizierenEventListener> inspizierenEventListeners = new LinkedList<>();

    public void add(KuchenInspizierenEventListener listener) {
        inspizierenEventListeners.add(listener);
    }

    public void remove(KuchenInspizierenEventListener listener) {
        inspizierenEventListeners.remove(listener);
    }

    public void handle(KuchenInspizierenEventImpl event) {
        for (KuchenInspizierenEventListener listener : inspizierenEventListeners) {
            listener.onKuchenInspizieren(event);
        }
    }
}
