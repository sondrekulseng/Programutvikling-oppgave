package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class KomponentController {

    @FXML
    void btnAvbryt(ActionEvent event) throws IOException {
        App.setRoot("admin");
    }
}
