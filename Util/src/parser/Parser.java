package parser;

import kuchenImpl.ObstkuchenImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public class Parser {

    public ObstkuchenImpl eingabenParsen(String eingabe) {
        String[] benutzereingaben = eingabe.split(" ");
        if (benutzereingaben.length < 5) {
            throw new IllegalArgumentException("Bitte geben Sie alle Kucheninformationen ein!");
        }

        String obstsorte = benutzereingaben[0];
        BigDecimal preis = new BigDecimal(benutzereingaben[1]);
        int naehrwert = Integer.parseInt(benutzereingaben[2]);
        Duration haltbarkeit = Duration.ofHours(Long.parseLong(benutzereingaben[3]));
        String[] allergene = benutzereingaben[4].split(",");

        return new ObstkuchenImpl(obstsorte, preis, naehrwert, haltbarkeit, List.of());
    }
}

