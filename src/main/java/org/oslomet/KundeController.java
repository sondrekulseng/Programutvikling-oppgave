package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class KundeController {
    @FXML
    private TableView<Datamaskin> tblDatamaskiner;

    @FXML
    private TableColumn<Datamaskin, String> prosessorCol;

    @FXML
    private TableColumn<Datamaskin, String> skjermkortCol;

    @FXML
    private TableColumn<Datamaskin, String> minneCol;

    @FXML
    private TableColumn<Datamaskin, String> harddiskCol;

    @FXML
    private TableColumn<Datamaskin, String> tastaturCol;

    @FXML
    private TableColumn<Datamaskin, String> datamusCol;

    @FXML
    private TableColumn<Datamaskin, String> skjermCol;

    @FXML
    private TableColumn<Datamaskin, Double> prisCol;

    @FXML
    public void initialize() {
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
