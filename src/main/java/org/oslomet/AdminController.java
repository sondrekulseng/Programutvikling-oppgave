package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class AdminController {

    @FXML
    void btnNyKomponent(ActionEvent event) throws IOException {
        App.setRoot("komponent");
    }

    @FXML
    void btnLoggUt(ActionEvent event) throws IOException {
        App.setRoot("start");
    }
}
