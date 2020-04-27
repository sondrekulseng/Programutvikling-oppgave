package org.oslomet;

import javafx.scene.control.Alert;

public class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
    public static boolean conversionSuccessful = true;

    @Override
    public Double fromString(String s) {
        try {
            Double result = super.fromString(s);
            conversionSuccessful = true;
            return result;
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Ugyldig data!");
            alert.setContentText("Du må taste inn et gyldig tall!");
            alert.showAndWait();
            conversionSuccessful = false;
            return 0.0;
        }
    }
}
