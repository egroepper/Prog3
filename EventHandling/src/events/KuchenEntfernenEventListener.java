package events;

import java.util.EventListener;

public interface KuchenEntfernenEventListener extends EventListener {
    void onKuchenEntfernen(KuchenEntfernenEventImpl event);
}
