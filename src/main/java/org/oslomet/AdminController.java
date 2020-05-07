package org.oslomet;

import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import java.io.IOException;

public class AdminController {

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
    private TextField txtKomponentNavn;

    @FXML
    private Text lblFinnKomponent;

    @FXML
    private Button btnReset;

    @FXML
    private Label lblEditMelding;

    private ThreadTest task;

    @FXML
    public void initialize() {
        prisCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        // fyll nedtrekkslister
        velgKategori.getItems().addAll("Vis alle","Prosessor","Skjermkort","Minne","Harddisk","Tastatur","Datamus","Skjerm");
        velgSortering.getItems().addAll("Alfabetisk","Pris (lav til høy)","Pris (høy til lav)");
        btnReset.setVisible(false);
        startThread(); // last data fra fil
    }

    private void startThread() {
        task = new ThreadTest();
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        velgKategori.setDisable(true);
        velgSortering.setDisable(true);
        txtKomponentNavn.setDisable(true);
        tblKomponenter.setPlaceholder(new Label("Laster data fra binær fil..."));
        th.start();
    }

    private void threadDone(WorkerStateEvent e) {
        velgKategori.setDisable(false);
        velgSortering.setDisable(false);
        txtKomponentNavn.setDisable(false);
        int antall = task.getValue();
        visInfoBoks("Lastet fra fil","Lastet "+task.getValue()+" elementer fra fil","");
        if (antall<1) {
            tblKomponenter.setPlaceholder(new Label("Ingen komponenter"));
        }
        sorterTabell();
    }

    private void threadFailed(WorkerStateEvent event) {
        tblKomponenter.setPlaceholder(new Label("Ingen komponeneter"));
        visAdvarselBoks("Feil","Feil ved innlasting av binær fil", "Lukk og prøv på nytt");
    }

    // filtrer etter kategori
    public ObservableList<Komponent> filtrerTabell() {
        String kategori = velgKategori.getValue();
        txtKomponentNavn.clear();
        btnReset.setVisible(false);
        lblFinnKomponent.setText("Søk i kategorien "+kategori.toLowerCase());
        ObservableList<Komponent> list;
        if (!kategori.equals("Vis alle")) {
            list = Register.filtrerListe(kategori);
        } else {
            lblFinnKomponent.setText("Søk i alle komponenter");
            list = Register.getKomponentListe();
        }
        return list;
    }

    // sorter alfabetisk, etter pris ...
    public void sorterTabell() {
        String sortering = velgSortering.getValue();
        ObservableList<Komponent> list = filtrerTabell();
        if (list.size()<1) {
            tblKomponenter.setPlaceholder(new Label("Ingen komponeneter"));
        }
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

    // endre verdier i tableview
    @FXML
    private void txtNavnEdited(TableColumn.CellEditEvent<Komponent, String> event) {
        event.getRowValue().setNavn(event.getNewValue());
        FileSaverJobj.save(); // lagre endringer til fil
        visInfoBoks("Lagret","Dine endringer ble lagret","");
    }

    @FXML
    private void doublePrisEdited(TableColumn.CellEditEvent<Komponent, Double> event) {
        try {
            event.getRowValue().setPris(event.getNewValue());
            FileSaverJobj.save();
            visInfoBoks("Lagret","Dine endringer ble lagret","");
        } catch (InvalidPriceException e) {
            visAdvarselBoks("Ugyldig pris","Du har skrevet en ugyldig pris", "");
        }
    }

    @FXML
    private void txtKategoriEdited(TableColumn.CellEditEvent<Komponent, String> event) {
        try {
            event.getRowValue().setKategori(event.getNewValue());
            FileSaverJobj.save();
            visInfoBoks("Lagret","Dine endringer ble lagret","");
        } catch (InvalidCategoriException e) {
            visAdvarselBoks("Ugyldig kategori","Du har skrevet en ugyldig kategori", "");
        }
    }

    // info boks
    private void visInfoBoks(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void visAdvarselBoks(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // slett rad
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
        FileSaverJobj.save();
        sorterTabell();
    }

    // søk etter komponent
    @FXML
    void btnFinnKomponent(ActionEvent event) {
        String navn = txtKomponentNavn.getText();
        ObservableList<Komponent> liste = Register.finnListe(navn, filtrerTabell());
        if (liste==null) { // tomt søk
            sorterTabell();
            return;
        }
        if (liste.size()<1) { // ingen treff
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ingen treff");
            alert.setHeaderText("Søket ga ingen treff.");
            alert.setContentText("Prøv igjen");
            alert.showAndWait();
            sorterTabell();
            txtKomponentNavn.setText("");
            btnReset.setVisible(false);
        } else { // søket ga treff
            tblKomponenter.setItems(liste);
            btnReset.setVisible(true);
            txtKomponentNavn.setText(navn);
        }
    }

    // tilbakestill søk
    @FXML
    void btnResetAction(ActionEvent event) throws IOException {
        tblKomponenter.setItems(filtrerTabell());
        btnReset.setVisible(false);
    }

    // annen side
    @FXML
    void btnNyKomponent(ActionEvent event) throws IOException {
        App.setRoot("komponent");
    }

    // annen side
    @FXML
    void btnLoggUt(ActionEvent event) throws IOException {
        App.setRoot("start");
    }
}
