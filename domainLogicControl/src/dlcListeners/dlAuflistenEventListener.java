package dlcListeners;

import automat.Verkaufsautomat;
import events.KuchenAuflistenEventImpl;
import events.KuchenAuflistenEventListener;
import events.ListeRueckwegEventImpl;
import handlers.ListeRueckwegEventHandler;
import kuchenImpl.ObstkuchenImpl;

public class dlAuflistenEventListener implements KuchenAuflistenEventListener {
    private final Verkaufsautomat vka_model;
    private final ListeRueckwegEventHandler listeRueckwegEventHandler;

    public dlAuflistenEventListener(Verkaufsautomat vkaModel, ListeRueckwegEventHandler listerueckwegeventhandler) {
        this.vka_model = vkaModel;
        this.listeRueckwegEventHandler = listerueckwegeventhandler;
    }

    @Override
    public void onKuchenAuflisten(KuchenAuflistenEventImpl event) {
        ObstkuchenImpl[] obstkuchenListe = vka_model.kuchenAuflisten();
        ListeRueckwegEventImpl listeRueckwegEvent = new ListeRueckwegEventImpl(this, obstkuchenListe);
        if (null != listeRueckwegEventHandler) {
            this.listeRueckwegEventHandler.handle(listeRueckwegEvent);
        }
    }
}
