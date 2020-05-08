package org.oslomet;

public class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
    public static boolean conversionSuccessful = true;

    @Override
    public Double fromString(String s) {
        try {
            Double result = super.fromString(s);
            conversionSuccessful = true;
            return result;
        } catch(NumberFormatException e) {
            conversionSuccessful = false;
            return 0.0;
        }
    }
}
