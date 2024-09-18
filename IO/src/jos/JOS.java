package jos;

import automat.Verkaufsautomat;

import java.io.*;

public class JOS {
    public JOS() {
    }

    // serialisieren der Geschaeftslogik
    // Quelle: "Java serialization" https://www.youtube.com/watch?v=DfbFTVNfkeI
    public static void speichernGL(Verkaufsautomat vka) {
        try {
            FileOutputStream fileOut = new FileOutputStream("AutomatenInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vka);
            out.close();
            fileOut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // de-serialisieren der Geschaeftslogik
    // Quelle: "Java serialization" https://www.youtube.com/watch?v=DfbFTVNfkeI
    public static Verkaufsautomat ladenGL() throws ClassNotFoundException {
        try {
            Verkaufsautomat vka;
            FileInputStream fileIn = new FileInputStream("AutomatenInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            vka = (Verkaufsautomat) in.readObject(); // casting
            in.close();
            fileIn.close();
            return vka;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
