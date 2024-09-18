package kuchenImpl;

import kuchen.Allergen;
import kuchen.Obstkuchen;
import verwaltung.Hersteller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

public class ObstkuchenImpl implements Obstkuchen, Serializable {
    private String obstsorte;
    private BigDecimal preis;
    private int naehrwert;
    private Duration haltbarkeit;
    private Collection<Allergen> allergene;
    private  Date einfuegedatum;
    private int fachnummer;
    private Date inspektionsdatum;


    // Konstruktor
    public ObstkuchenImpl(String obstsorte, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergene) {
        this.obstsorte = obstsorte;
        this.preis = preis;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.allergene = allergene;
        this.einfuegedatum = new Date(); // Zustaendigkeit Automat, nicht Benutzer
        this.fachnummer = getFachnummer(); // Zustaendigkeit Automat, nicht Benutzer
    }


    // setter
    public void setEinfuegedatum(Date einfuegedatum) {
        this.einfuegedatum = einfuegedatum;
    }

    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    public void setInspektionsdatum(Date inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }


    @Override
    public Hersteller getHersteller() {
        return null;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return this.allergene;
    }

    @Override
    public int getNaehrwert() {
        return this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return this.haltbarkeit;
    }
    @Override
    public String getObstsorte() {
        return this.obstsorte;
    }

    @Override
    public BigDecimal getPreis() {
        return this.preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return this.inspektionsdatum;
    }

    @Override
    public int getFachnummer() {
        return this.fachnummer;
    }

    // Einfuegedatum konvertieren
    // Quelle ChatGPT (s. Screenshot im Ordner 'Quellen')
    private LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    // Zustaendigkeit woanders?
    public Duration getVerbleibendeHaltbarkeit() {
        // Quelle ChatGPT (s. Screenshot im Ordner 'Quellen')
        LocalDateTime einfuegedatumLDT = toLocalDateTime(einfuegedatum);
        LocalDateTime verfallsdatum = einfuegedatumLDT.plus(haltbarkeit);
        LocalDateTime aktuell = LocalDateTime.now();
        return Duration.between(aktuell, verfallsdatum);
    }
    @Override
    public String toString() {
        // Quelle ChatGPT (s. Screenshot im Ordner 'Quellen')
        long tage = getVerbleibendeHaltbarkeit().toDays();
        long stunden = getVerbleibendeHaltbarkeit().minusDays(tage).toHours();

        return "Obstsorte: " + this.obstsorte + ", Fachnummer: " + this.fachnummer + ", Inspektionsdatum: " + this.inspektionsdatum + ", verbleibende Haltbarkeit : " + tage + " Tag(e) und " + stunden + " Stunden" + "\n" ;
    }
}
