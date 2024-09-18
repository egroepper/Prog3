package dlcListeners;

import automat.Verkaufsautomat;
import events.KuchenEinfuegenEventImpl;
import events.KuchenEinfuegenEventListener;

public class dlEinfuegenEventListener implements KuchenEinfuegenEventListener {
    private final Verkaufsautomat vka_model;

    public dlEinfuegenEventListener(Verkaufsautomat vkaModel) {
        this.vka_model = vkaModel;
    }

    @Override
    public void onKuchenEinfuegen(KuchenEinfuegenEventImpl event) throws Exception {
        this.vka_model.kuchenEinfuegen(event.getObstsorte(), event.getPreis(), event.getNaehrwert(), event.getHaltbarkeit(), event.getAllergene());
    }
}
