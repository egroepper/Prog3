package automat;

import kuchen.Allergen;
import kuchenImpl.ObstkuchenImpl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;


public class Verkaufsautomat implements Serializable {
    public ObstkuchenImpl[] obstkuchenListe;
    private final int KAPAZITAET;
    private static final long SERIAL_VERSION_UID = 1L;

    // Konstruktor
    public Verkaufsautomat(int kapazitaet) throws Exception {
        this.KAPAZITAET = kapazitaet;

        if (kapazitaet < 0) {
            throw new Exception("Kapazitaet muss mindestens 0 betragen");
        }
        this.obstkuchenListe = new ObstkuchenImpl[kapazitaet]; // Obstkuchenlisten mit der Laenge der angegebenen Kapazitaet
    }

    // CRUD fuer Obstkuchen
    // C fuer create
    public synchronized void kuchenEinfuegen(String obstsorte, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergene) {
        ObstkuchenImpl neuObstkuchen = new ObstkuchenImpl(obstsorte, preis, naehrwert, haltbarkeit, allergene);

        for (int i = 0; i < obstkuchenListe.length; i++) {
            if (obstkuchenListe[i] == null) {
                obstkuchenListe[i] = neuObstkuchen;

                obstkuchenListe[i].setEinfuegedatum(new Date());
                obstkuchenListe[i].setFachnummer(i); // Fachnummer ist gleich der Index des Arrays
                break;
            }
        }
    }

    // R fuer read
    public synchronized ObstkuchenImpl[] kuchenAuflisten() {
        return Arrays.copyOf(obstkuchenListe, obstkuchenListe.length); // gibt Kopie anstatt Original zurueck
    }

    // U fuer update
    public synchronized void inspektionsdatumAendern(int fach) {
        if (fach >= 0 && fach < obstkuchenListe.length && obstkuchenListe[fach] != null) {
            Date aktuellesDatum = new Date();
            obstkuchenListe[fach].setInspektionsdatum(aktuellesDatum);
            // throw new NoSuchElementException("ungueltige Fachnummer");
        }
    }

    // D fuer delete
    public synchronized void kuchenEntfernen(int fach)  {
        if (fach >= 0 && fach < obstkuchenListe.length) {
            obstkuchenListe[fach] = null;
            // throw new NoSuchElementException("ungueltige Fachnummer");
        }
    }
}
