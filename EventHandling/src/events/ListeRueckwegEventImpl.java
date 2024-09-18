package events;

import kuchenImpl.ObstkuchenImpl;

import java.util.EventObject;

public class ListeRueckwegEventImpl extends EventObject {
    private final ObstkuchenImpl[] obstkuchenListe;
    public ListeRueckwegEventImpl(Object source, ObstkuchenImpl[] obstkuchenliste) {
        super(source);
        this.obstkuchenListe = obstkuchenliste;
    }

    public ObstkuchenImpl[] getObstkuchenListe() {
        return obstkuchenListe;
    }
}
