package events;

import java.util.EventListener;

public interface KuchenAuflistenEventListener extends EventListener {
    void onKuchenAuflisten(KuchenAuflistenEventImpl event);
}
