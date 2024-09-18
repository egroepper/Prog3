package events;

import java.util.EventListener;

public interface KuchenInspizierenEventListener extends EventListener {
    void onKuchenInspizieren(KuchenInspizierenEventImpl event);
}
