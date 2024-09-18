package simulationThreads;
import automat.Verkaufsautomat;
import events.KuchenEntfernenEventImpl;
import handlers.KuchenEntfernenEventHandler;

import java.util.Random;

public class KuchenEntfernenThread implements Runnable {
    private final Verkaufsautomat vka;
    private final Random random;
    private final KuchenEntfernenEventHandler kuchenEntfernenEventHandler;

    public KuchenEntfernenThread(Verkaufsautomat vka, KuchenEntfernenEventHandler keeh) {
        this.vka = vka;
        this.random = new Random();
        this.kuchenEntfernenEventHandler = keeh;
    }

    // Quelle: https://www.straub.as/java/threads/synchronized.html
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (vka) {
                    if (this.vka.obstkuchenListe != null && vka.obstkuchenListe.length >= 0) { // Kapazität von 0 ist ein zulaessiger Wert
                        int indexEntfernen = random.nextInt(vka.obstkuchenListe.length);

                        if (indexEntfernen >= 0 && indexEntfernen < vka.obstkuchenListe.length && vka.obstkuchenListe[indexEntfernen] != null) {
                            KuchenEntfernenEventImpl kuchenEntfernenEvent = new KuchenEntfernenEventImpl(this, indexEntfernen);
                            this.kuchenEntfernenEventHandler.handle(kuchenEntfernenEvent);
                            System.out.println("Kuchen wurde entfernt");
                        }
                    }
                }
            } catch (Exception e) {
                //System.err.println("Fehler beim Entfernen");
            }
        }
    }
}
