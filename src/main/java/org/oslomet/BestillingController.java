package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.io.IOException;

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
    public void initialize() {
        if (Register.getKomponentListe().size() == 0) {
            try {
                Register.getKomponentListe().addAll(FileOpenerJobj.readFile());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        // fyll nedtrekkslister med verdier fra komponent lister
        velgProsessor.getItems().addAll(Register.filtrerListe("Prosessor"));
        velgSkjermkort.getItems().addAll(Register.filtrerListe("Skjermkort"));
        velgMinne.getItems().addAll(Register.filtrerListe("Minne"));
        velgLagring.getItems().addAll(Register.filtrerListe("Harddisk"));
        velgTastatur.getItems().addAll(Register.filtrerListe("Tastatur"));
        velgDatamus.getItems().addAll(Register.filtrerListe("Datamus"));
        velgSkjerm.getItems().addAll(Register.filtrerListe("Skjerm"));
        lblKvittering.setText("Bruk menyen til venstre for å konfigurere en ny pc.\nAlle feltene må fylles ut.");
    }

    @FXML
    void btnAvbryt(ActionEvent event) throws IOException {
        App.setRoot("kunde");
    }

    @FXML
    void btnNyBestilling(ActionEvent event) throws IOException {
        Komponent[] k = getSelected(); // hent verdier
        if (validering(k)) { // validering godkjent
            double totPris = Math.round(totPris(k)*100.0)/100.0; // total pris
            Register.setDatamaskinListe(new Datamaskin(k[0],k[1],k[2],k[3],k[4],k[5],k[6],totPris)); // ny pc
            try {
                FileSaverTxt.save();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bestilling lagret");
                alert.setHeaderText("Din bestilling ble lagret");
                alert.setContentText("Bestilling ble skrevet til fil");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil ved lagring av bestilling");
                alert.setHeaderText("Klarte ikke å lagre bestilling");
                alert.setContentText("Prøv på nytt");
                alert.showAndWait();
            }
            App.setRoot("kunde");
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
        double totPris = Math.round(totPris(k)*100.0)/100.0; // total pris
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
}
