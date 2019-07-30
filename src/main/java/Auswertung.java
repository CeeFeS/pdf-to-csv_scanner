

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.ArrayList;

public class Auswertung {

    private ArrayList<String> informationen;
    private int anzahl_pdfs;

    public void run(String ordner) {
        this.informationen = new ArrayList<>();
        informationen = verarbeite_pdftext(alle_Dateien_in_ordner(ordner));
    }

    public File[] alle_Dateien_in_ordner(String ordner) {
        /**alle Dateien in angegeben Ordner entdecken, die mit der Endung .pdf sind die richtigen*/

        File folder = new File(ordner);
        File[] fileNames = folder.listFiles();
        return fileNames;
    }

    public ArrayList<String> verarbeite_pdftext(File[] fileNames) {
        ArrayList<String> pdf_infos = new ArrayList<String>();
        /**alle Dateien in angegeben ordner durchgehen*/
        for (File file : fileNames) {
            anzahl_pdfs++;
            StringBuilder sb = new StringBuilder();
            try (PDDocument document = PDDocument.load(new File(file.getPath()))) {
                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setSortByPosition(true);
/**alle Seiten des PDF-Dokuments durchgehen*/
                for (int p = 1; p <= document.getNumberOfPages(); ++p) {

                    stripper.setStartPage(p);
                    stripper.setEndPage(p);

                    String text = stripper.getText(document);

                    String lines[] = text.split("\\r?\\n");
/**alle Zeilen der PDF-Seite durchgehen*/
                    for (String s : lines) {
                        if (s.contains("Number RECEIPT")) {
                            String[] tmp = s.split("of");
                            String datum = tmp[1];
                            datum = datum.trim();
                            sb.append(datum);
                        }
                        if (s.contains("LINK ID")) {
                            String tmp[] = s.split("ID");
                            String link_id;
                            if (tmp.length >= 2) {
                                link_id = tmp[1];
                                link_id = link_id.replace("- FINAL ORDER", "").trim();
                                sb.append(link_id + ";");
                            } else {
                                sb.append("");
                            }


                        }
                        if (!(s.contains("LINK ID")) && s.contains("FINAL ORDER")) {
                            sb.append(" ;");
                        }
                        if (s.contains("Nummer:")) {
                            String[] tmp = s.split(":");
                            String nummer = tmp[1];
                            nummer = nummer.trim();
                            sb.append(nummer + ";");
                        }
                        System.out.println(sb.toString());
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            /** Inhalt eine Doukemts entspricht einem String der in eine Array gepackt wird, welches alle relevanten Inhalte der Dokumente enthält*/
            pdf_infos.add(sb.toString());
        }
        return pdf_infos;
    }

    public ArrayList<String> getInformationen() {
        return informationen;
    }

    public int getAnzahl_pdfs() {
        return anzahl_pdfs;
    }
}

