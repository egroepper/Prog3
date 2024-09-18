package cli;

import events.KuchenAuflistenEventImpl;
import events.KuchenEinfuegenEventImpl;
import events.KuchenEntfernenEventImpl;
import events.KuchenInspizierenEventImpl;
import kuchenImpl.ObstkuchenImpl;
import parser.Parser;

import java.util.Scanner;


class ModusHandling {
    protected final Scanner Scanner;
    private final Parser Parser;

    public ModusHandling(Scanner scanner) {
        Scanner = scanner;
        Parser = new Parser();
    }

    void handleC(Benutzeroberflaeche bof) {
        while (true) {
            try {
//                String Moduseingabe = Scanner.nextLine();
//                if (Moduseingabe.startsWith(":")) {
//                    break;
//                }
                String benutzereingaben = Scanner.nextLine();
                ObstkuchenImpl obstkuchen = Parser.eingabenParsen(benutzereingaben);
                if (null != bof.getKuchenEinfuegenEventHandler()) {
                    KuchenEinfuegenEventImpl kuchenEinfuegenEvent = new KuchenEinfuegenEventImpl(bof, obstkuchen.getObstsorte(), obstkuchen.getPreis(), obstkuchen.getNaehrwert(), obstkuchen.getHaltbarkeit(), obstkuchen.getAllergene());
                    bof.getKuchenEinfuegenEventHandler().handle(kuchenEinfuegenEvent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } break;
        }
    }


    void handleR(Benutzeroberflaeche bof) {
        while (true) {
            try {
                String benutzereingabe = Scanner.nextLine();
                if (benutzereingabe.equalsIgnoreCase("kuchen Obstkuchen")) {
                    if (null != bof.getKuchenAuflistenEventHandler()) {
                        KuchenAuflistenEventImpl kuchenAuflistenEvent = new KuchenAuflistenEventImpl(bof);
                        bof.getKuchenAuflistenEventHandler().handle(kuchenAuflistenEvent);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } break;
        }
    }



    void handleU (Benutzeroberflaeche bof) {
        while (true) {
            try {
//                String Moduseingabe = Scanner.nextLine();
//                if (Moduseingabe.startsWith(":")) {
//                    break;
//                }
                int eingabe = Scanner.nextInt();
                Scanner.nextLine(); //nextLine() nach nextInt() um den Zeilenumbruch zu entfernen. Quelle: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
                if (null != bof.getKuchenInspizierenEventHandler()) {
                    KuchenInspizierenEventImpl kuchenInspizierenEvent = new KuchenInspizierenEventImpl(bof, eingabe);
                    bof.getKuchenInspizierenEventHandler().handle(kuchenInspizierenEvent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } break;
        }
    }

    void handleD (Benutzeroberflaeche bof) {
        while (true) {
            try {
//                String Moduseingabe = Scanner.nextLine();
//                if (Moduseingabe.startsWith(":")) {
//                    break;
//                }
                int eingabe = Scanner.nextInt();
                Scanner.nextLine(); //nextLine() nach nextInt() um den Zeilenumbruch zu entfernen. Quelle: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
                if (null != bof.getKuchenEntfernenEventHandler()) {
                    KuchenEntfernenEventImpl kuchenEntfernenEvent = new KuchenEntfernenEventImpl(bof, eingabe);
                    bof.getKuchenEntfernenEventHandler().handle(kuchenEntfernenEvent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } break;
        }
    }
}
