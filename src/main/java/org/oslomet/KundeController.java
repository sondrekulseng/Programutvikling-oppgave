package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class KundeController {

    @FXML
    void btnNyBestilling(ActionEvent event) throws IOException  {
        App.setRoot("bestilling");
    }

    @FXML
    void btnLoggUt(ActionEvent event) throws IOException  {
        App.setRoot("start");
    }
}
