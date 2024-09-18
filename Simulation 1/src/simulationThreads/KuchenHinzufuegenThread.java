package simulationThreads;

import automat.Verkaufsautomat;
import events.KuchenEinfuegenEventImpl;
import handlers.KuchenEinfuegenEventHandler;
import kuchen.Allergen;
import kuchenImpl.ObstkuchenImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class KuchenHinzufuegenThread implements Runnable{
    private final Verkaufsautomat vka;
    private final ObstkuchenImpl[] randomKuchenArray;
    private final Random random;
    private final KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler;



    public KuchenHinzufuegenThread(Verkaufsautomat vka, KuchenEinfuegenEventHandler keeh) {
        this.vka = vka;
        this.random = new Random();
        this.kuchenEinfuegenEventHandler = keeh;

        this.randomKuchenArray = new ObstkuchenImpl[] {
                new ObstkuchenImpl("Zitrone", new BigDecimal("4.20"), 290, Duration.ofDays(5), List.of(Allergen.Gluten)),
                new ObstkuchenImpl("Apfel", new BigDecimal("3.30"), 330, Duration.ofDays(6), List.of(Allergen.Haselnuss)),
                new ObstkuchenImpl("Himbeere", new BigDecimal("5.00"), 420, Duration.ofDays(2), List.of(Allergen.Sesamsamen)),
                new ObstkuchenImpl("Rhabarber", new BigDecimal("4.80"), 510, Duration.ofDays(3), List.of(Allergen.Erdnuss)),
                new ObstkuchenImpl("Heidelbeere", new BigDecimal("3.10"), 310, Duration.ofDays(2), List.of(Allergen.Gluten)),
                new ObstkuchenImpl("Birne", new BigDecimal("2.70"), 470, Duration.ofDays(4), List.of(Allergen.Haselnuss)),
        };
    }

    // Quelle: https://www.straub.as/java/threads/synchronized.html
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (vka) {
                    int index = random.nextInt(randomKuchenArray.length);
                    ObstkuchenImpl randomKuchen = randomKuchenArray[index];

                    KuchenEinfuegenEventImpl kuchenEinfuegenEvent = new KuchenEinfuegenEventImpl(this, randomKuchen.getObstsorte(), randomKuchen.getPreis(), randomKuchen.getNaehrwert(), randomKuchen.getHaltbarkeit(), randomKuchen.getAllergene());
                    this.kuchenEinfuegenEventHandler.handle(kuchenEinfuegenEvent);
                    System.out.println("                         Kuchen wurde eingefuegt"); // nur fuer bessere Sichtbarkeit auf der Konsole
                }
            } catch (Exception e) {
                // System.err.println("Fehler beim Einfuegen");
            }
        }
    }
}
