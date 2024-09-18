package automat;
import kuchen.Allergen;
import kuchenImpl.ObstkuchenImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class VerkaufsautomatTest {

    @org.junit.jupiter.api.Test
    void Konstruktor_negativeKapazitaetWirftException() throws Exception { // Quelle https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-tests
        // setup
        int negKapazitaet = -1;

        // unter Test und Zusicherung
        assertThrows(Exception.class, () -> new Verkaufsautomat(negKapazitaet));
    }

        @org.junit.jupiter.api.Test
    void kuchenEinfuegen_FuegtEinenKuchenEin() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(10);
        String obstsorte = "Birne";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene);

        // Zusicherung
        assertNotNull(vka.obstkuchenListe[0]);
    }


    @org.junit.jupiter.api.Test
    void kuchenEinfuegen_InNaechstVerfuegbaresFachNachEntfernen() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(4); // 4 damit geguckt, wird, ob auch wirklich in Fach 1 und nicht Fach 3 eingefügt wird
        String obstsorte = "Kirsche";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 0
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 1
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 2 (Fach 3 ist leer)
        vka.kuchenEntfernen(1); // Fach 1 ist jetzt auch leer
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // sollte in Fach 1 und nicht Fach 3 eingefuegt werden

        // Zusicherung
        assertNotNull(vka.obstkuchenListe[1]);
    }

    @org.junit.jupiter.api.Test
    void kuchenEinfuegen_InNaechstVerfuegbaresFach() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        String obstsorte = "Kirsche";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 0
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 1 und nicht irgendein ein anderes Fach

        // Zusicherung
        assertNotNull(vka.obstkuchenListe[1]);
    }

    @org.junit.jupiter.api.Test
    void kuchenAuflisten_NachEinemKuchen() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(1);
        String obstsorte = "Kirsche";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenAuflisten();

        // Zusicherung
        assertNotNull(vka.obstkuchenListe);
    }

    @org.junit.jupiter.api.Test
    void kuchenAuflisten_WennAutomatNeuUndOhneKuchen() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(1);

        // unter Test
        vka.kuchenAuflisten();

        // Zusicherung
        assertNull(vka.obstkuchenListe[0]);
    }

    @org.junit.jupiter.api.Test
    void kuchenAuflisten_ListenLengthSoGrossWieKapazitaet() throws Exception {
        // setup
        int kapazitaet = 2;
        Verkaufsautomat vka = new Verkaufsautomat(kapazitaet);

        // unter Test
        ObstkuchenImpl[] obstkuchenListe = vka.kuchenAuflisten();

        // Zusicherung
        assertEquals(kapazitaet, obstkuchenListe.length);
    }

    @org.junit.jupiter.api.Test
    void kuchenAuflisten_NichtsRutschtInDerListeNachEinfuegenUndLoeschen() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        String obstsorte = "Kirsche";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 0
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 1
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 2
        vka.kuchenEntfernen(1);
        vka.kuchenAuflisten();

        // Zusicherung
        assertNull(vka.obstkuchenListe[1]);
    }

    @org.junit.jupiter.api.Test
    void kuchenAuflisten_StimmtInhaltDerKuchenlisteMitKopieDerKuchenlisteUeberein() throws Exception { // Ueberpruefung anhand der Obstsorte
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        String obstsorte = "Zitrone";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene);
        ObstkuchenImpl[] obstkuchenListe = vka.kuchenAuflisten();

        // Zusicherung
        assertEquals(obstsorte, obstkuchenListe[0].getObstsorte());
    }

    @org.junit.jupiter.api.Test
    void kuchenAuflisten_AenderungenAnKopieDerKuchenlisteBetrifftNichtDasOriginal() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        String obstsorte = "Zitrone";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene);
        ObstkuchenImpl[] obstkuchenListe = vka.kuchenAuflisten();
        obstkuchenListe[0] = null;

        // Zusicherung
        assertNotNull(vka.obstkuchenListe[0]);
    }


    @org.junit.jupiter.api.Test
    void inspektionsdatumAendern_StimmtMitAktuellemDatumUeberein() throws Exception { //anhand der Zeit
        //setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        String obstsorte = "Zitrone";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene);
        //Quelle: https://stackoverflow.com/questions/1671001/compare-date-objects-with-different-levels-of-precision
        Date inspektionsdatumHeute = new Date(System.currentTimeMillis() + 120000); // + 2 Minuten Toleranzzeitraum
        int fach = 0;

        //unter Test
        vka.inspektionsdatumAendern(fach);
        ObstkuchenImpl[] obstkuchenListe = vka.kuchenAuflisten();

        //Quelle: https://stackoverflow.com/questions/1671001/compare-date-objects-with-different-levels-of-precision
        //Zusicherung
        assertTrue(obstkuchenListe[fach].getInspektionsdatum().getTime() - inspektionsdatumHeute.getTime() <= 120000);
    }

    @org.junit.jupiter.api.Test
    void inspektionsdatumAendern_AnNichtExistierendemFach() throws Exception{
        //setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        int nichtExistierendesFach = -1; // 0 ist noch ok

        // unter Test
        vka.inspektionsdatumAendern(nichtExistierendesFach);
        ObstkuchenImpl[] obstkuchenListe = vka.kuchenAuflisten();

        // Zusicherung
        for (ObstkuchenImpl obstkuchen : obstkuchenListe)  // Es geht nur so, weil sonst immer Array Index out of bounds für -1
            assertNull(obstkuchen);
        }

    @org.junit.jupiter.api.Test
    void inspektionsdatumAendern_AnFachOhneKuchen() throws Exception{
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(3);
        int fachOhneKuchen = 1;
        vka.obstkuchenListe[fachOhneKuchen] = null;

        // unter Test
        vka.inspektionsdatumAendern(fachOhneKuchen);
        ObstkuchenImpl[] obstkuchenListe = vka.kuchenAuflisten();

        // Zusicherung
        assertNull(obstkuchenListe[fachOhneKuchen]);
    }

    @org.junit.jupiter.api.Test
    void kuchenEntfernen_EntferntEinenKuchen() throws Exception{
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(1);
        String obstsorte = "Kirsche";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene); // Fach 0
        vka.kuchenEntfernen(0);

        // Zusicherung
        assertNull(vka.obstkuchenListe[0]);
    }

    @org.junit.jupiter.api.Test
    void kuchenEntfernen_wennKeinKuchenImAutomatDannNichtEntfernen() throws Exception{
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(1);

        // unter Test
        vka.kuchenEntfernen(1);

        // Zusicherung
        assertNull(vka.obstkuchenListe[0]);
    }

    @org.junit.jupiter.api.Test
    void kuchenEntfernen_wennFachLeerDannNichtEntfernen() throws Exception {
        // setup
        Verkaufsautomat vka = new Verkaufsautomat(2);
        String obstsorte = "Kirsche";
        BigDecimal preis = new BigDecimal("4.50");
        int naehrwert = 450;
        Duration haltbarkeit = Duration.ofSeconds(2);
        List<Allergen> allergene = List.of(Allergen.Gluten);

        // unter Test
        vka.kuchenEinfuegen(obstsorte, preis, naehrwert, haltbarkeit, allergene);
        ObstkuchenImpl[] Zustandvorher = vka.obstkuchenListe.clone();
        vka.kuchenEntfernen(1); // Fach 1 ist leer
        ObstkuchenImpl[] Zustandnachher = vka.obstkuchenListe.clone();

        // Zusicherung
        assertArrayEquals(Zustandvorher, Zustandnachher);
    }
}
