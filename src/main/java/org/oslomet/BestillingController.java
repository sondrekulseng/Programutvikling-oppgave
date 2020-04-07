package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
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
    private Button btnNyBestilling;

    @FXML
    void btnAvbryt(ActionEvent event) throws IOException {
        App.setRoot("kunde");
    }

    @FXML
    void btnNyBestilling(ActionEvent event) throws IOException {
        Komponent[] k = getSelected(); // hent verdier
        double totPris = totPris(k); // hent total pris

        // opprett datamasking med komponenter
        Datamaskin d = new Datamaskin(k[0],k[1],k[2],k[3],k[4],k[5],k[6],totPris);
        System.out.println(d.getProsessor()+" "+d.getTotPris());
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

        // legg verdier i en array
        Komponent[] k = {prosessor,skjermkort,minne,lagring,tastatur,datamus,skjerm};

        print(k); // print kvittering med valgte komponenter og totpris
        validering(k); // valider at bruker har fylt ut alle felt

        return k; // returner verdier fra nedtrekksliste som array
    }

    double totPris(Komponent[] k) {
        double totPris = 0;
        if (k[0] != null) {
            totPris = totPris+k[0].getPris();
        }
        if (k[1] != null) {
            totPris = totPris+k[1].getPris();
        }
        if (k[2] != null) {
            totPris = totPris+k[2].getPris();
        }
        if (k[3] != null) {
            totPris = totPris+k[3].getPris();
        }
        if (k[4] != null) {
            totPris = totPris+k[4].getPris();
        }
        if (k[5] != null) {
            totPris = totPris+k[5].getPris();
        }
        if (k[6] != null) {
            totPris = totPris+k[6].getPris();
        }
        return totPris;
    }

    void validering(Komponent[] k) {
        if (k[0]==null||k[1]==null||k[2]==null||k[3]==null||k[4]==null||k[5]==null||k[6]==null) {
            btnNyBestilling.setDisable(true);
        } else {
            btnNyBestilling.setDisable(false);
        }
    }

    void print(Komponent[] k) {
        double totPris = totPris(k);
        // print kvittering
        lblKvittering.setText("");
        lblKvittering.setText("KVITTERING: \n"+
                "Prosessor: "+k[0]+"\n"+
                "Skjermkort: "+k[1]+"\n"+
                "Minne: "+k[2]+"\n"+
                "Lagring: "+k[3]+"\n"+
                "Tastatur: "+k[4]+"\n"+
                "Datamus: "+k[5]+"\n"+
                "Skjerm: "+k[6]+"\n"+
                "Total pris: "+totPris+" kr");
    }

    @FXML
    public void initialize() {
        velgProsessor.getItems().addAll(Register.getProsessorListe());
        velgSkjermkort.getItems().addAll(Register.getSkjermkortListe());
        velgMinne.getItems().addAll(Register.getMinneListe());
        velgLagring.getItems().addAll(Register.getLagringListe());
        velgTastatur.getItems().addAll(Register.getTastaturListe());
        velgDatamus.getItems().addAll(Register.getDatamusListe());
        velgSkjerm.getItems().addAll(Register.getSkjermListe());
        btnNyBestilling.setDisable(true);
    }
}
