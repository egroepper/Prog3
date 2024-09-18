package dlcListeners;

import automat.Verkaufsautomat;
import events.KuchenInspizierenEventImpl;
import events.KuchenInspizierenEventListener;

public class dlInspizierenEventListener implements KuchenInspizierenEventListener {
    private final Verkaufsautomat VKA_MODEL;

    public dlInspizierenEventListener(Verkaufsautomat vkaModel) {
        this.VKA_MODEL = vkaModel;
    }

    @Override
    public void onKuchenInspizieren(KuchenInspizierenEventImpl event) {
        //this.VKA_MODEL.inspektionsdatumAendern(event.getFachnummer());
        this.VKA_MODEL.inspektionsdatumAendern(event.getFachnummer());
    }
}
