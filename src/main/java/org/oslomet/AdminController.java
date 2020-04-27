package org.oslomet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AdminController {

    String[] k = {"Vis alle","Prosessor","Skjermkort","Minne","Harddisk","Tastatur","Datamus","Skjerm"};

    @FXML
    private ChoiceBox<String> velgKategori;

    @FXML
    private ChoiceBox<String> velgSortering;

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
        prisCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        velgKategori.getItems().addAll(k);
        velgSortering.getItems().addAll("Alfabetisk","Pris (lav til høy)","Pris (høy til lav)");
        sorterTabell();
    }

    // filtrer etter kategori
    public ObservableList<Komponent> filtrerTabell() {
        String kategori = velgKategori.getValue();
        ObservableList<Komponent> list;
        if (!kategori.equals("Vis alle")) {
            list = Register.filtrerListe(kategori);
        } else {
            list = Register.getKomponentListe();
        }
        return list;
    }

    // sorter alfabetisk, etter pris ...
    public void sorterTabell() {
        String sortering = velgSortering.getValue();
        ObservableList<Komponent> list = filtrerTabell();
        if (sortering.equals("Alfabetisk")) {
            // alfabetisk
            tblKomponenter.setItems(list.sorted());
        } else if (sortering.equals("Pris (lav til høy)")) {
            // pris, stigende
            tblKomponenter.setItems(Register.sorterListe(sortering,list));
        } else if (sortering.equals("Pris (høy til lav)")) {
            // pris, synkende
            tblKomponenter.setItems(Register.sorterListe(sortering,list));
        }
    }

    @FXML
    private void txtNavnEdited(TableColumn.CellEditEvent<Komponent, String> event) {
        event.getRowValue().setNavn(event.getNewValue());
    }

    @FXML
    private void doublePrisEdited(TableColumn.CellEditEvent<Komponent, Double> event) {
        event.getRowValue().setPris(event.getNewValue());
    }

    @FXML
    void btnSlettRad(ActionEvent event) throws IOException {
        Komponent k = tblKomponenter.getSelectionModel().getSelectedItem();
        ObservableList<Komponent> list = Register.getKomponentListe();
        for (int i=0; i<list.size(); i++) {
            if (k == list.get(i)) {
                // slett valgt komponent
                Register.slettKomponent(i);
            }
        }
        sorterTabell();
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
