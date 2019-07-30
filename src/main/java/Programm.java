import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Scanner;

import static javafx.application.Application.launch;

public class Programm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------PDF-zu-CSV:version1.0------------------------------\n-------------------------------------------------------------------");

        Auswertung auswertung = new Auswertung();
        System.out.println("Pfad zum Ordner mit den PDFs: \n");
        String s = scanner.nextLine();
        auswertung.run(s);
        System.out.println("\n---------------------------------------------------");
        System.out.println("\nAnzahl der gescannten PDFs:" + auswertung.getAnzahl_pdfs());
        System.out.println("\n---------------------------------------------------");
        System.out.println("Pfad zum Abspeichern der CSV: \n");
        String f = scanner.nextLine();
        CSVkonvertierung csvk = new CSVkonvertierung(f, auswertung.getInformationen());
        System.out.println("CSV erstellt unter:" + f);
    }

}

