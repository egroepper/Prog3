package handlers;

import events.KuchenEinfuegenEventImpl;
import events.KuchenEinfuegenEventListener;

import java.util.LinkedList;
import java.util.List;

public class KuchenEinfuegenEventHandler {
    private final List<KuchenEinfuegenEventListener> einfuegenEventListeners = new LinkedList<>();

    public void add(KuchenEinfuegenEventListener listener) {
        einfuegenEventListeners.add(listener);
    }

    public void remove(KuchenEinfuegenEventListener listener) {
        einfuegenEventListeners.remove(listener);
    }

    public void handle(KuchenEinfuegenEventImpl event) throws Exception {
        for (KuchenEinfuegenEventListener listener : einfuegenEventListeners) {
            listener.onKuchenEinfuegen(event);
        }
    }
}
