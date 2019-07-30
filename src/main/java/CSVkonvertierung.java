import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class CSVkonvertierung {
    private String ordner;
    private ArrayList<String> infos;
    private StringBuilder sb;

    public CSVkonvertierung(String ordner, ArrayList<String> infos) {
        this.ordner = ordner;
        this.infos = infos;
        verarbeiten();
        schreiben();
    }

    private void verarbeiten() {
        this.sb = new StringBuilder();
        sb.append("Nummer;LINK ID;ATI Number RECEIPT of\n");
        for (String s : infos) {
            sb.append(s).append("\n");
        }
    }

    private void schreiben() {
        Writer fw = null;
        try {
            fw = new FileWriter(ordner + "\\PDF.csv");
            fw.write(sb.toString());

        } catch (IOException e1) {
            e1.printStackTrace();
            System.err.println("Die Datei konnte nicht erstellt werden");
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e2) {
                }
        }
    }
}

