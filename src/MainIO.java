import automat.Verkaufsautomat;
import dlcListeners.dlEinfuegenEventListener;
import handlers.KuchenEinfuegenEventHandler;
import jos.JOS;
import kuchen.Allergen;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MainIO {

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            int kapazitaet = Integer.parseInt(args[0]); // Quelle: https://stackoverflow.com/questions/890966/what-is-the-string-args-parameter-in-the-main-method
            Verkaufsautomat vka = new Verkaufsautomat(kapazitaet);
            // wenn nichts ueber args angegeben ist, dann:
            // Verkaufsautomat vka = new Verkaufsautomat(20);

            vka.kuchenEinfuegen("Apfel", new BigDecimal(2.90), 270, Duration.ofDays(4), List.of(Allergen.Haselnuss));
            vka.kuchenEinfuegen("Birne", new BigDecimal(2.90), 270, Duration.ofDays(4), List.of(Allergen.Sesamsamen));
            vka.kuchenEinfuegen("Zitrone", new BigDecimal(2.90), 270, Duration.ofDays(4), List.of(Allergen.Gluten));
            vka.kuchenEinfuegen("Banane", new BigDecimal(2.90), 270, Duration.ofDays(4), List.of(Allergen.Erdnuss));
            vka.kuchenEinfuegen("Erdbeere", new BigDecimal(2.90), 270, Duration.ofDays(4), List.of(Allergen.Gluten));
            vka.kuchenEinfuegen("Blaubeere", new BigDecimal(2.90), 270, Duration.ofDays(4), List.of(Allergen.Gluten));


            System.out.println("Zustand vor Speicherung:");
            JOS.speichernGL(vka);
            System.out.println(Arrays.toString(vka.kuchenAuflisten()));

            System.out.println("-------------------------");

            System.out.println("Zustand nach Speicherung:");
            Verkaufsautomat vkaGeladen = JOS.ladenGL();
            System.out.println(Arrays.toString(vkaGeladen.kuchenAuflisten()));
        }
    }
}
