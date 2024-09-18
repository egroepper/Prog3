package dlcListeners;

import automat.Verkaufsautomat;
import events.KuchenEntfernenEventImpl;
import events.KuchenEntfernenEventListener;

public class dlEntfernenEventListener implements KuchenEntfernenEventListener {
    private final Verkaufsautomat vka_model;

    public dlEntfernenEventListener(Verkaufsautomat vkaModel) {
       this.vka_model = vkaModel;
    }

    @Override
    public void onKuchenEntfernen(KuchenEntfernenEventImpl event) {
        this.vka_model.kuchenEntfernen(event.getFachnummer());
    }
}
