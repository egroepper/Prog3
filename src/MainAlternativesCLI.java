import automat.Verkaufsautomat;
import cli.Benutzeroberflaeche;
import cliControlListener.CLIListeRueckwegEventListener;
import dlcListeners.dlAuflistenEventListener;
import dlcListeners.dlEinfuegenEventListener;
import dlcListeners.dlEntfernenEventListener;
import dlcListeners.dlInspizierenEventListener;
import handlers.*;


import automat.Verkaufsautomat;
import cli.Benutzeroberflaeche;
import cliControlListener.CLIListeRueckwegEventListener;
import dlcListeners.dlAuflistenEventListener;
import dlcListeners.dlEinfuegenEventListener;
import dlcListeners.dlEntfernenEventListener;
import dlcListeners.dlInspizierenEventListener;
import handlers.*;

/* zum Testen copy paste:
Apfel 4.50 400 48 Gluten
Birne 4.50 400 48 Gluten
Erdbeere 4.50 400 48 Gluten
Zitrone 4.50 400 48 Gluten

kuchen Obstkuchen
*/

// Inspektionsdatum aendern und Kuchen entfernen sind deaktiviert
public class MainAlternativesCLI {
        public static void main(String[] args) throws Exception {
            if (args.length > 0) {
                int kapazitaet = Integer.parseInt(args[0]); // Quelle: https://stackoverflow.com/questions/890966/what-is-the-string-args-parameter-in-the-main-method
                Verkaufsautomat vka = new Verkaufsautomat(kapazitaet);
                Benutzeroberflaeche bof = new Benutzeroberflaeche();

                // Rueckweg von GL zum CLI fuer "r"
                ListeRueckwegEventHandler listeRueckwegEventHandler = new ListeRueckwegEventHandler();
                listeRueckwegEventHandler.add(new CLIListeRueckwegEventListener(bof));

                // Hinweg von CLI zur GL fuer CRUD
                // C
                KuchenEinfuegenEventHandler einfuegenEventHandler = new KuchenEinfuegenEventHandler();
                einfuegenEventHandler.add(new dlEinfuegenEventListener(vka));

                // R
                KuchenAuflistenEventHandler auflistenEventHandler = new KuchenAuflistenEventHandler();
                auflistenEventHandler.add(new dlAuflistenEventListener(vka, listeRueckwegEventHandler));

                /*
                // U
                KuchenInspizierenEventHandler inspizierenEventHandler = new KuchenInspizierenEventHandler();
                inspizierenEventHandler.add(new dlInspizierenEventListener(vka));

                // D
                KuchenEntfernenEventHandler entfernenEventHandler = new KuchenEntfernenEventHandler();
                entfernenEventHandler.add(new dlEntfernenEventListener(vka));
                 */

                // setter
                bof.setKuchenEinfuegenEventHandler(einfuegenEventHandler);
                bof.setKuchenAuflistenEventHandler(auflistenEventHandler);
                // bof.setKuchenInspizierenEventHandler(inspizierenEventHandler);
                // bof.setKuchenEntfernenEventHandler(entfernenEventHandler);

                bof.cli(); 
            }
        }
    }

