package events;

import java.util.EventListener;

public interface KuchenEinfuegenEventListener extends EventListener {
    void onKuchenEinfuegen(KuchenEinfuegenEventImpl event) throws Exception;
}
