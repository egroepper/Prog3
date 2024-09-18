package cliControlListener;

import cli.Benutzeroberflaeche;
import events.ListeRueckwegEventImpl;
import events.ListeRueckwegEventListener;

public class CLIListeRueckwegEventListener implements ListeRueckwegEventListener {
    private final Benutzeroberflaeche bofModel;


    public CLIListeRueckwegEventListener(Benutzeroberflaeche bofModel) {
        this.bofModel = bofModel;
    }

    @Override
    public void onListeRueckwegEvent(ListeRueckwegEventImpl event) {
        this.bofModel.kuchenAuflisten(event.getObstkuchenListe());
    }
}
