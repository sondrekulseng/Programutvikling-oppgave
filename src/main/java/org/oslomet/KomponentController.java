package org.oslomet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class KomponentController {

    String[] k = {"Prosessor","Skjermkort","Minne","Harddisk","Tastatur","Datamus","Skjerm"};

    @FXML
    public void initialize() {
        velgKategori.getItems().addAll(k);
    }

    @FXML
    private TextField txtNavn;

    @FXML
    private TextField txtPris;

    @FXML
    private Label lblMelding;

    @FXML
    private ChoiceBox<String> velgKategori;

    @FXML
    void btnAvbryt(ActionEvent event) throws IOException {
        App.setRoot("admin");
    }

    @FXML
    void btnLeggTil(ActionEvent event) {
        String kategori = velgKategori.getValue();
        String navn = txtNavn.getText();
        double pris = 0;
        lblMelding.setText("");
        if (txtNavn.getText().isEmpty() || txtPris.getText().isEmpty()) {
            lblMelding.setText("Alle feltene må fylles ut");
            return;
        }
        try {
            pris = validerPris(txtPris.getText());
        } catch (InvalidPriceException e) {
            lblMelding.setText(e.getMessage());
            return;
        }
        if (kategori == null) {
            lblMelding.setText("Du må velge en kategori");
            return;
        }

        // sjekk om dette produktet allerede finnes
        if (navnFinnes(navn)) {
            lblMelding.setText("Dette navnet er allerede tatt");
            return;
        }

        Register.setKomponentListe(new Komponent(navn,pris,kategori.toLowerCase()));
        FileSaverJobj.save();

        txtNavn.clear();
        txtPris.clear();
        lblMelding.setText("Komponent lagt til");
    }

    boolean navnFinnes(String navn) {
        boolean navnFinnes = false;
        ObservableList<Komponent> list = Register.getKomponentListe();
        for (int i=0; i<list.size(); i++) {
            if (navn.equals(list.get(i).getNavn())) { // produkt finnes
                navnFinnes = true;
                break;
            }
        }
        return navnFinnes;
    }

    double validerPris(String input) throws InvalidPriceException {
        double pris = -1;
        try {
            pris = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidPriceException("Pris må være et positivt tall");
        }
        if (pris < 1) {
            throw new InvalidPriceException("Pris må være et positivt tall");
        }
        return pris;

    }
}
