package cli;

import handlers.*;
import kuchenImpl.ObstkuchenImpl;

import java.util.Arrays;
import java.util.Scanner;


public class Benutzeroberflaeche {
    private final ModusHandling Modushandler;

    // Handler fuer CRUD
    private KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler;
    private KuchenAuflistenEventHandler kuchenAuflistenEventHandler;
    private KuchenInspizierenEventHandler kuchenInspizierenEventHandler;
    private KuchenEntfernenEventHandler kuchenEntfernenEventHandler;

    // von der GL zurueck zum CLI
    public void setKuchenEinfuegenEventHandler(KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler) {
        this.kuchenEinfuegenEventHandler = kuchenEinfuegenEventHandler;
    }

    public KuchenEinfuegenEventHandler getKuchenEinfuegenEventHandler() {
        return kuchenEinfuegenEventHandler;
    }

    public void setKuchenAuflistenEventHandler(KuchenAuflistenEventHandler kuchenAuflistenEventHandler) {
        this.kuchenAuflistenEventHandler = kuchenAuflistenEventHandler;
    }

    public KuchenAuflistenEventHandler getKuchenAuflistenEventHandler() {
        return kuchenAuflistenEventHandler;
    }

    public void setKuchenInspizierenEventHandler(KuchenInspizierenEventHandler kuchenInspizierenEventHandler) {
        this.kuchenInspizierenEventHandler = kuchenInspizierenEventHandler;
    }

    public KuchenInspizierenEventHandler getKuchenInspizierenEventHandler() {
        return kuchenInspizierenEventHandler;
    }

    public void setKuchenEntfernenEventHandler(KuchenEntfernenEventHandler kuchenEntfernenEventHandler) {
        this.kuchenEntfernenEventHandler = kuchenEntfernenEventHandler;
    }

    public KuchenEntfernenEventHandler getKuchenEntfernenEventHandler() {
        return kuchenEntfernenEventHandler;
    }


    public Benutzeroberflaeche() {
        Scanner scanner = new Scanner(System.in);
        this.Modushandler = new ModusHandling(scanner);
    }

    public void cli() {
        boolean run = true;

        do {
            String modus = this.Modushandler.Scanner.nextLine().toLowerCase();

            switch (modus) {
                case ":c":
                    Modushandler.handleC(this);
                    break;
                case ":r":
                    Modushandler.handleR(this);
                    break;
                case ":u":
                    Modushandler.handleU(this);
                    break;
                case ":d":
                    Modushandler.handleD(this);
                    break;

                default:
                    System.out.println("ungueltiger Befehl");
            }
        } while (run);
    }

    public void kuchenAuflisten(ObstkuchenImpl[] obstkuchenListe) {
        System.out.println(Arrays.toString(obstkuchenListe));
    }
}

