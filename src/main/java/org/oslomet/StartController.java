package org.oslomet;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController {

    @FXML
    void switchToAdmin(ActionEvent event) throws IOException {
        App.setRoot("admin");
    }

    @FXML
    void switchToKunde(ActionEvent event) throws IOException {
        App.setRoot("kunde");
    }
}
