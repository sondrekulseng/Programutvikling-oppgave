package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class BestillingController {
    @FXML
    private ChoiceBox<Komponent> velgProsessor;
    @FXML
    private ChoiceBox<Komponent> velgSkjermkort;
    @FXML
    private ChoiceBox<Komponent> velgMinne;
    @FXML
    private ChoiceBox<Komponent> velgLagring;
    @FXML
    private ChoiceBox<Komponent> velgTastatur;
    @FXML
    private ChoiceBox<Komponent> velgDatamus;
    @FXML
    private ChoiceBox<Komponent> velgSkjerm;
    @FXML
    private Label lblKvittering;

    @FXML
    void btnAvbryt(ActionEvent event) throws IOException {
        App.setRoot("kunde");
    }

    @FXML
    void btnNyBestilling(ActionEvent event) throws IOException {
        Komponent[] k = getSelected(); // hent verdier
        if (validering(k)) { // validering godkjent
            double totPris = totPris(k); // total pris
            Register.setDatamaskinListe(new Datamaskin(k[0],k[1],k[2],k[3],k[4],k[5],k[6],totPris)); // ny pc
            App.setRoot("kunde"); //
        } else { // validering er ikke godkjent
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fyll ut felter");
            alert.setHeaderText("Du må fylle ut alle feltene");
            alert.setContentText("Lukk dialogboksen og prøv på nytt");
            alert.showAndWait();
        }
    }

    @FXML
    Komponent[] getSelected() {
        // hent verdier fra nedtrekksliste
        Komponent prosessor = velgProsessor.getValue();
        Komponent skjermkort = velgSkjermkort.getValue();
        Komponent minne = velgMinne.getValue();
        Komponent lagring = velgLagring.getValue();
        Komponent tastatur = velgTastatur.getValue();
        Komponent datamus = velgDatamus.getValue();
        Komponent skjerm = velgSkjerm.getValue();

        // legg verdier i array
        Komponent[] k = {prosessor,skjermkort,minne,lagring,tastatur,datamus,skjerm};

        print(k); // print kvittering med valgte komponenter og totpris

        return k; // returner verdier fra nedtrekksliste som array
    }

    double totPris(Komponent[] k) {
        double totPris = 0;
        for (int i=0; i<k.length; i++) {
            if (k[i] != null) {
                totPris = totPris+k[i].getPris();
            }
        }
        return totPris;
    }

    boolean validering(Komponent[] k) {
        if (k[0]==null||k[1]==null||k[2]==null||k[3]==null||k[4]==null||k[5]==null||k[6]==null) {
            return false;
        } else {
            return true;
        }
    }

    void print(Komponent[] k) {
        double totPris = totPris(k);
        String[] liste = {"Prosessor","Skjermkort","Minne","Harddisk","Tastatur","Datamus","Skjerm"};
        int antallElementer = 0;
        String ut = "";
        // print kvittering
        for (int i=0; i<k.length; i++) {
            if (k[i]!=null) {
                ut += liste[i]+": "+k[i]+"\n";
                antallElementer++;
            }
        }
        if (antallElementer > 0) {
            ut += "\nTotal sum: "+totPris+" kr";
            lblKvittering.setText(ut);
        }
    }

    @FXML
    public void initialize() {
        // fyll nedtrekkslister med verdier fra komponent lister
        velgProsessor.getItems().addAll(Register.getProsessorListe());
        velgSkjermkort.getItems().addAll(Register.getSkjermkortListe());
        velgMinne.getItems().addAll(Register.getMinneListe());
        velgLagring.getItems().addAll(Register.getHarddiskListe());
        velgTastatur.getItems().addAll(Register.getTastaturListe());
        velgDatamus.getItems().addAll(Register.getDatamusListe());
        velgSkjerm.getItems().addAll(Register.getSkjermListe());
        lblKvittering.setText("Bruk menyen til venstre for å konfigurere en ny pc.\nAlle feltene må fylles ut.");
    }
}
