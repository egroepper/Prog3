package events;

import java.util.EventListener;

public interface ListeRueckwegEventListener extends EventListener {
    void onListeRueckwegEvent(ListeRueckwegEventImpl event);
}
