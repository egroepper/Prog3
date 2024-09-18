package events;

import kuchen.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.EventObject;

public class KuchenEinfuegenEventImpl extends EventObject {

    private final String OBSTSORTE;
    private final BigDecimal PREIS;
    private final int NAEHRWERT;
    private final Duration HALTBARKEIT;
    private final Collection<Allergen> ALLLERGENE;

    public KuchenEinfuegenEventImpl(Object source, String obstsorte, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> alllergene) {
        super(source);
        this.OBSTSORTE = obstsorte;
        this.PREIS = preis;
        this.NAEHRWERT = naehrwert;
        this.HALTBARKEIT = haltbarkeit;
        this.ALLLERGENE = alllergene;
    }

    // getter
    public String getObstsorte() {
        return OBSTSORTE;
    }
    public BigDecimal getPreis() {
        return PREIS;
    }
    public int getNaehrwert() {
        return NAEHRWERT;
    }
    public Duration getHaltbarkeit() {
        return HALTBARKEIT;
    }
    public Collection<Allergen> getAllergene() {
        return ALLLERGENE;
    }
}
