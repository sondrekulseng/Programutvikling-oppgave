package org.oslomet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class BestillingController {
    @FXML
    private ChoiceBox<String> velgProsessor;

    @FXML
    void btnAvbryt(ActionEvent event) throws IOException {
        App.setRoot("kunde");
    }
}
