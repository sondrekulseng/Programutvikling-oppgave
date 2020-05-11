package org.oslomet;

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
            pris = Double.parseDouble(txtPris.getText());
        } catch (NumberFormatException e) {
            pris = 0;
        }

        try {
            Register.setKomponentListe(new Komponent(navn,pris,kategori.toLowerCase()));
            lblMelding.setText("Komponent lagt til og skrevet til fil");
        } catch (Exception e) {
            lblMelding.setText(e.getMessage());
            return;
        }

        try {
            FileSaverJobj.save();
        } catch (IOException e) {
            lblMelding.setText("Feil ved lagring av fil.");
        }

        txtNavn.clear();
        txtPris.clear();
    }
}
