package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class KundeController {
    @FXML
    private TableView<Datamaskin> tblDatamaskiner;

    @FXML
    private TableColumn<Datamaskin, Komponent> prosessorCol;

    @FXML
    private TableColumn<Datamaskin, Komponent> skjermkortCol;

    @FXML
    private TableColumn<Datamaskin, Komponent> minneCol;

    @FXML
    private TableColumn<Datamaskin, Komponent> harddiskCol;

    @FXML
    private TableColumn<Datamaskin, Komponent> tastaturCol;

    @FXML
    private TableColumn<Datamaskin, Komponent> datamusCol;

    @FXML
    private TableColumn<Datamaskin, Komponent> skjermCol;

    @FXML
    private TableColumn<Datamaskin, Double> prisCol;

    @FXML
    public void initialize() {
        if (Register.getDatamaskinListe().size() == 0) {
            int antallLinjer = 0;
            int antallFeil = 0;
            // hent bestillinger fra fil
            try {
                antallLinjer = FileOpenerTxt.readFile();
                antallFeil = FileOpenerTxt.feilMeldinger.size();
                if (antallLinjer > 0) { // filen inneholder data
                    int antallGodkjent = antallLinjer-antallFeil;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Leste fra fil");
                    alert.setHeaderText("Leste "+antallGodkjent+" av "+antallLinjer+" bestillinger fra fil");
                    alert.setContentText(antallFeil+" bestillinger med feil ble funnet");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil");
                alert.setHeaderText("Kunne ikke laste fra fil");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

            // print ut feilmeldinger og hvilken linje de er på
            for (int i=0; i<antallFeil; i++) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil");
                alert.setHeaderText(FileOpenerTxt.feilLinjeNr.get(i));
                alert.setContentText(FileOpenerTxt.feilMeldinger.get(i));
                alert.showAndWait();
            }
            FileOpenerTxt.feilMeldinger.clear();
            FileOpenerTxt.feilLinjeNr.clear();
        }

        tblDatamaskiner.setItems(Register.getDatamaskinListe());
    }

    @FXML
    void btnNyBestilling(ActionEvent event) throws IOException  {
        App.setRoot("bestilling");
    }

    @FXML
    void btnLoggUt(ActionEvent event) throws IOException  {
        App.setRoot("start");
    }
}
