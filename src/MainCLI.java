import automat.Verkaufsautomat;
import cli.Benutzeroberflaeche;
import cliControlListener.CLIListeRueckwegEventListener;
import dlcListeners.dlAuflistenEventListener;
import dlcListeners.dlEinfuegenEventListener;
import dlcListeners.dlEntfernenEventListener;
import dlcListeners.dlInspizierenEventListener;
import handlers.*;
import netControlListeners.NetAuflistenEventListener;
import netControlListeners.NetEinfuegenEventListener;
import netControlListeners.NetEntfernenEventListener;
import netControlListeners.NetInspizierenEventListener;
import tcp.TCPClientImpl;


/* zum Testen copy paste:
Apfel 4.50 400 48 Gluten
Birne 4.50 400 48 Gluten
Erdbeere 4.50 400 48 Gluten
Zitrone 4.50 400 48 Gluten

kuchen Obstkuchen
*/

public class MainCLI {
        public static void main(String[] args) throws Exception {
                // zuerst haendisch MainServerTCP starten
                        if (args[1].equalsIgnoreCase("TCP")) {

                                TCPClientImpl tcpClient = new TCPClientImpl(4711);
                                Benutzeroberflaeche bof = new Benutzeroberflaeche();

                                // C
                                KuchenEinfuegenEventHandler kuchenEinfuegenEventHandler = new KuchenEinfuegenEventHandler();
                                bof.setKuchenEinfuegenEventHandler(kuchenEinfuegenEventHandler);
                                kuchenEinfuegenEventHandler.add(new NetEinfuegenEventListener(tcpClient));

                                // R
                                KuchenAuflistenEventHandler kuchenAuflistenEventHandler = new KuchenAuflistenEventHandler();
                                bof.setKuchenAuflistenEventHandler(kuchenAuflistenEventHandler);
                                kuchenAuflistenEventHandler.add(new NetAuflistenEventListener(tcpClient));

                                // R Rueckweg
                                ListeRueckwegEventHandler listeRueckwegEventHandler = new ListeRueckwegEventHandler();
                                tcpClient.setListeRueckwegEventHandler(listeRueckwegEventHandler);
                                listeRueckwegEventHandler.add(new CLIListeRueckwegEventListener(bof));

                                // U
                                KuchenInspizierenEventHandler kuchenInspizierenEventHandler = new KuchenInspizierenEventHandler();
                                bof.setKuchenInspizierenEventHandler(kuchenInspizierenEventHandler);
                                kuchenInspizierenEventHandler.add(new NetInspizierenEventListener(tcpClient));

                                // D
                                KuchenEntfernenEventHandler kuchenEntfernenEventHandler = new KuchenEntfernenEventHandler();
                                bof.setKuchenEntfernenEventHandler(kuchenEntfernenEventHandler);
                                kuchenEntfernenEventHandler.add(new NetEntfernenEventListener(tcpClient));

                                bof.cli();

                        } else { // ohne TCP
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

                            // U
                            KuchenInspizierenEventHandler inspizierenEventHandler = new KuchenInspizierenEventHandler();
                            inspizierenEventHandler.add(new dlInspizierenEventListener(vka));

                            // D
                            KuchenEntfernenEventHandler entfernenEventHandler = new KuchenEntfernenEventHandler();
                            entfernenEventHandler.add(new dlEntfernenEventListener(vka));

                            // setter
                            bof.setKuchenEinfuegenEventHandler(einfuegenEventHandler);
                            bof.setKuchenAuflistenEventHandler(auflistenEventHandler);
                            bof.setKuchenInspizierenEventHandler(inspizierenEventHandler);
                            bof.setKuchenEntfernenEventHandler(entfernenEventHandler);

                            bof.cli(); // ganz ans Ende setzten, da infinite loop
                        }
        }
}
