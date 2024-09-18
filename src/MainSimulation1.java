import automat.Verkaufsautomat;
import dlcListeners.dlEinfuegenEventListener;
import dlcListeners.dlEntfernenEventListener;
import handlers.KuchenEinfuegenEventHandler;
import handlers.KuchenEntfernenEventHandler;
import simulationThreads.KuchenEntfernenThread;
import simulationThreads.KuchenHinzufuegenThread;

public class MainSimulation1 {

    public static void main(String[] args) throws Exception {
        // Kapazitaet wird per Kommandozeilenargument uebergeben, 0 ist ein zulaessiger Wert
        if (args.length > 0) {
            int kapazitaet = Integer.parseInt(args[0]); // Quelle: https://stackoverflow.com/questions/890966/what-is-the-string-args-parameter-in-the-main-method
            Verkaufsautomat vka = new Verkaufsautomat(kapazitaet);

            KuchenEinfuegenEventHandler einfuegenEventHandler = new KuchenEinfuegenEventHandler();
            einfuegenEventHandler.add(new dlEinfuegenEventListener(vka));

            KuchenEntfernenEventHandler entfernenEventHandler = new KuchenEntfernenEventHandler();
            entfernenEventHandler.add(new dlEntfernenEventListener(vka));

            // Threads anlegen
            KuchenEntfernenThread ket = new KuchenEntfernenThread(vka, entfernenEventHandler);
            KuchenHinzufuegenThread kht = new KuchenHinzufuegenThread(vka, einfuegenEventHandler);

            Thread einfuegenThread = new Thread(ket);
            Thread entfernenThread = new Thread(kht);

            // zum Testen
            einfuegenThread.setName("einfuegen Thread");
            entfernenThread.setName("entfernen Thread");

            einfuegenThread.start();
            entfernenThread.start();
        }
    }
}
