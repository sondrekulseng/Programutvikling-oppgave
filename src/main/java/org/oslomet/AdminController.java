package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.stream.Collectors;

public class AdminController {

    String[] k = {"Vis alle","Prosessor","Skjermkort","Minne","Harddisk","Tastatur","Datamus","Skjerm"};

    @FXML
    private ChoiceBox<String> velgKategori;

    @FXML
    private TableView<Komponent> tblKomponenter;

    @FXML
    private TableColumn<Komponent, String> navnCol;

    @FXML
    private TableColumn<Komponent, Double> prisCol;

    @FXML
    private TableColumn<Komponent, String> kategoriCol;

    @FXML
    public void initialize() {
        velgKategori.getItems().addAll(k);
        tblKomponenter.setItems(Register.getKomponentListe());
    }

    public void sorterTabell() {
        String kategori = velgKategori.getValue();
        if (!kategori.equals("Vis alle")) {
            tblKomponenter.setItems(Register.filtrerListe(kategori));
        } else {
            tblKomponenter.setItems(Register.getKomponentListe());
        }
    }

    @FXML
    void btnNyKomponent(ActionEvent event) throws IOException {
        App.setRoot("komponent");
    }

    @FXML
    void btnLoggUt(ActionEvent event) throws IOException {
        App.setRoot("start");
    }
}
