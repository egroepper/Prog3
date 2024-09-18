package handlers;

import events.ListeRueckwegEventImpl;
import events.ListeRueckwegEventListener;

import java.util.LinkedList;
import java.util.List;

public class ListeRueckwegEventHandler {
    private final List<ListeRueckwegEventListener> listeRueckwegEventListeners = new LinkedList<>();
    public void add(ListeRueckwegEventListener listener) {
        listeRueckwegEventListeners.add(listener);
    }
    public void remove(ListeRueckwegEventListener listener) {
        listeRueckwegEventListeners.remove(listener);
    }

    public void handle(ListeRueckwegEventImpl event) {
        for (ListeRueckwegEventListener listener : listeRueckwegEventListeners) {
            listener.onListeRueckwegEvent(event);
        }
    }
}
